package com.codesmell.service.impl;

import com.codesmell.domain.dto.BucketPolicyConfigDto;
import com.codesmell.domain.dto.FileDto;
import com.codesmell.domain.exception.ApiException;
import com.codesmell.domain.exception.Asserts;
import com.codesmell.service.IMinioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Slf4j
@ApplicationScoped
public class MinioServiceImpl implements IMinioService {

    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024; // 2MB

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/gif"
    );

    @ConfigProperty(name = "minio.bucket-name")
    String bucketName;

    @Inject
    MinioClient minioClient;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Collection<FileDto> listObjects(String path) {
        var objects = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(path)
                .delimiter("/")
                .includeUserMetadata(true)
                .build()
        );

        Collection<FileDto> result = new ArrayList<>();
        if (Objects.nonNull(objects)) {
            for (var object : objects) {
                try {
                    var item = object.get();
                    var isFolder = item.isDir();
                    result.add(FileDto.builder()
                            .fileName(item.objectName())
                            .filePath(item.objectName())
                            .isLeaf(!isFolder)
                            .size(item.size())
                            .fileType(isFolder ? null : item.userMetadata().getOrDefault("content-type", null))
                            .lastModified(isFolder ? null : item.lastModified())
                            .build());
                } catch (Exception e) {
                    log.error("Error listing objects from MinIO", e);
                    Asserts.fail();
                }
            }
        }

        return result;
    }

    @SneakyThrows
    @Override
    public FileDto uploadObject(FileUpload file, String path) {
        String contentType = file.contentType();

        if (Objects.isNull(contentType) || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            Asserts.unsupportedMediaType("Unsupported file type. Allowed types are JPEG, PNG, and GIF.");
        }

        if (file.size() > MAX_FILE_SIZE) {
            Asserts.requestEntityTooLarge("File size exceeds the maximum limit of 2MB.");
        }

        if (StringUtils.isBlank(path)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            path = sdf.format(new Date());
        }

        try (var bao = new ByteArrayOutputStream()) {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExist) {
                log.info("The bucket {} already exists!", bucketName);
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(bucketName);
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(objectMapper.writeValueAsString(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }

            Thumbnails.of(file.uploadedFile().toFile()).size(1280, 720).toOutputStream(bao);
            var bis = new ByteArrayInputStream(bao.toByteArray());

            String fileName = file.fileName();
            var response = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path + "/" + fileName)
                    .stream(bis, bis.available(), -1)
                    .contentType(contentType)
                    .build());

            return FileDto.builder()
                    .filePath(response.object())
                    .fileType(contentType)
                    .fileName(fileName)
                    .build();
        } catch (Exception e) {
            log.error("Error uploading file to MinIO", e);
            throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public FileDto fileDetails(String path) {
        StatObjectResponse stat = null;
        try {
            stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .build()
            );
        } catch (Exception e) {
            log.error("Error getting file details from MinIO", e);
            Asserts.fail();
        }

        return FileDto.builder()
                .fileName(stat.object())
                .filePath(stat.object())
                .fileType(stat.contentType())
                .build();
    }

    @Override
    public InputStream downloadObject(String path) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .build()
            );
        } catch (Exception e) {
            log.error("Error downloading file from MinIO", e);
            throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteObject(String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::" + bucketName + "/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(Collections.singletonList(statement))
                .build();
    }
}

package com.codesmell.config;

import io.minio.MinioClient;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RegisterForReflection(targets = {
        io.minio.GetObjectArgs.class,
        io.minio.PutObjectArgs.class,
        io.minio.SetBucketPolicyArgs.class,
        io.minio.MakeBucketArgs.class,
        io.minio.BucketExistsArgs.class,
        io.minio.StatObjectArgs.class,
        io.minio.RemoveObjectArgs.class,
        io.minio.ListObjectsArgs.class,
})
@Slf4j
@ApplicationScoped
public class MinioConfig {

    @ConfigProperty(name = "minio.url")
    String url;

    @ConfigProperty(name = "minio.access-key")
    String accessKey;

    @ConfigProperty(name = "minio.secret-key")
    String secretKey;

    @Produces
    @Singleton
    public MinioClient minioClient() {
        log.info("Creating MinioClient");
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

}

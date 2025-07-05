package com.codesmell.controller;

import com.codesmell.service.IMinioService;
import com.codesmell.domain.api.ApiResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Slf4j
@Path("/api/minio")
public class MinioController {

    @Inject
    IMinioService minioService;

    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult<?> files(@QueryParam("path") String path) {
        var result = minioService.listObjects(path);
        return ApiResult.success(result);
    }

    @GET
    @Path("/file")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult<?> file(@QueryParam("path") String path) {
        var result = minioService.fileDetails(path);
        return ApiResult.success(result);
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult<?> upload(@RestForm("file") FileUpload file,
                               @QueryParam("path") String path) {
        var result = minioService.uploadObject(file, path);
        return ApiResult.success(result);
    }

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@QueryParam("path") String path) {
        var result = minioService.downloadObject(path);

        return Response.ok(result)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path)
                .build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult<?> delete(@QueryParam("objectName") String objectName) {
        minioService.deleteObject(objectName);
        return ApiResult.success(null);
    }

}

package com.codesmell.config;

import com.codesmell.domain.api.ApiResult;
import com.codesmell.domain.exception.ApiException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

public class GlobalExceptionConfig {
}

@Slf4j
@Provider
class GlobalExceptionHandle implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        log.error(e.getMessage(), e);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ApiResult.failed())
                .build();
    }
}

@Slf4j
@Provider
class ApiExceptionHandle implements ExceptionMapper<ApiException> {
    @Override
    public Response toResponse(ApiException e) {
        log.error(e.getMessage(), e);
        int statusCode = e.getStatus().getStatusCode();
        String message = e.getStatus().getReasonPhrase();
        ApiResult<?> result = new ApiResult<>(statusCode, message, null);
        return Response
                .status(e.getStatus())
                .entity(result)
                .build();
    }
}
package com.codesmell.domain.api;

import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResult<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase(), data);
    }

    public static <T> ApiResult<T> success(T data, String message) {
        return new ApiResult<>(Response.Status.OK.getStatusCode(), message, data);
    }

    public static <T> ApiResult<T> failed(String message) {
        return new ApiResult<>(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message, null);
    }

    public static <T> ApiResult<T> failed() {
        return failed(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public static <T> ApiResult<T> validateFailed(String message) {
        return new ApiResult<>(Response.Status.BAD_REQUEST.getStatusCode(), message, null);
    }

    public static <T> ApiResult<T> validateFailed() {
        return validateFailed(Response.Status.BAD_REQUEST.getReasonPhrase());
    }

    public static <T> ApiResult<T> unauthorized(T data) {
        return new ApiResult<>(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED.getReasonPhrase(), data);
    }

    public static <T> ApiResult<T> forbidden(T data) {
        return new ApiResult<>(Response.Status.FORBIDDEN.getStatusCode(), Response.Status.FORBIDDEN.getReasonPhrase(), data);
    }

}

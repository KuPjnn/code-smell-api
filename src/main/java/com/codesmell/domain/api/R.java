package com.codesmell.domain.api;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class R<T> {

    private int code;
    private String message;
    private T data;

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase(), data);
    }

    public static <T> R<T> ok(T data, String message) {
        return new R<>(Response.Status.OK.getStatusCode(), message, data);
    }

    public static <T> R<T> failed(String message) {
        return new R<>(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message, null);
    }

    public static <T> R<T> failed() {
        return failed(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public static <T> R<T> badRequest(String message) {
        return new R<>(Response.Status.BAD_REQUEST.getStatusCode(), message, null);
    }

    public static <T> R<T> badRequest() {
        return badRequest(Response.Status.BAD_REQUEST.getReasonPhrase());
    }

    public static <T> R<T> unauthorized(T data) {
        return new R<>(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED.getReasonPhrase(), data);
    }

    public static <T> R<T> forbidden(T data) {
        return new R<>(Response.Status.FORBIDDEN.getStatusCode(), Response.Status.FORBIDDEN.getReasonPhrase(), data);
    }

}

package com.codesmell.domain.exception;

import jakarta.ws.rs.core.Response;

public class Asserts {

    public static void fail() {
        throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR);
    }

    public static void fail(String message) {
        throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR, message);
    }

    public static void badRequest() {
        throw new ApiException(Response.Status.BAD_REQUEST);
    }

    public static void badRequest(String message) {
        throw new ApiException(Response.Status.BAD_REQUEST, message);
    }

    public static void requestEntityTooLarge() {
        throw new ApiException(Response.Status.REQUEST_ENTITY_TOO_LARGE);
    }

    public static void requestEntityTooLarge(String message) {
        throw new ApiException(Response.Status.REQUEST_ENTITY_TOO_LARGE, message);
    }

    public static void unsupportedMediaType() {
        throw new ApiException(Response.Status.UNSUPPORTED_MEDIA_TYPE);
    }

    public static void unsupportedMediaType(String message) {
        throw new ApiException(Response.Status.UNSUPPORTED_MEDIA_TYPE, message);
    }

}

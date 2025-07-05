package com.codesmell.domain.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private Response.Status status;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Response.Status status) {
        super(status.getReasonPhrase());
        this.status = status;
    }

    public ApiException(Response.Status status, String message) {
        super(message);
        this.status = status;
    }

}

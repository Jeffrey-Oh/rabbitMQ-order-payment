package com.jeffrey.orderapi.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {
    public UnauthorizedException(HttpStatus status, String message) {
        super(status, message);
    }
}

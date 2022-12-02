package com.example.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}

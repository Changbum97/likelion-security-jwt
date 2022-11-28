package com.security.jwt.exception;

import com.security.jwt.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }

    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity<?> customizedExceptionHandler(CustomizedException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(e.getMessage() + "\n" + e.getErrorCode().getMessage());
    }
}

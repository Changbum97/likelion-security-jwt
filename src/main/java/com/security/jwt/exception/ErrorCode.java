package com.security.jwt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "Username is duplicated."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email is duplicated");

    private HttpStatus status;
    private String message;
}

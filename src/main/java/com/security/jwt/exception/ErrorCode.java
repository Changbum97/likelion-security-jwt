package com.security.jwt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "Username is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Username Not Found"),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "Invalid Password"),
    ;

    private HttpStatus status;
    private String message;
}

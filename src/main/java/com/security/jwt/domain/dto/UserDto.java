package com.security.jwt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private String username;
    private String password;
    private String email;
}

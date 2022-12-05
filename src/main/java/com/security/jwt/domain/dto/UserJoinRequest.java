package com.security.jwt.domain.dto;

import com.security.jwt.domain.User;
import com.security.jwt.domain.UserRole.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserJoinRequest {

    private String username;
    private String password;
    private String email;

    public User toEntity(String password) {
        return User.builder()
                .username(this.username)
                .password(password)
                .email(this.email)
                .role(UserRole.USER)
                .build();
    }
}

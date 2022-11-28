package com.security.jwt.domain.dto;

import com.security.jwt.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {

    private String username;
    private String password;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .build();
    }
}

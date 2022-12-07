package com.security.jwt.domain.dto;

import com.security.jwt.domain.entity.User;
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

    public User toEntity(String password) {
        return User.builder()
                .username(this.username)
                .password(password)
                .role(UserRole.USER)
                .build();
    }
}

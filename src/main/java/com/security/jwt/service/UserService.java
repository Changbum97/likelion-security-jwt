package com.security.jwt.service;

import com.security.jwt.domain.dto.UserDto;
import com.security.jwt.domain.dto.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDto join(UserJoinRequest request) {
        return new UserDto("", "", "");
    }
}

package com.security.jwt.service;

import com.security.jwt.domain.User;
import com.security.jwt.domain.dto.UserDto;
import com.security.jwt.domain.dto.UserJoinRequest;
import com.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest request) {

        // username 중복 체크 로직
        userRepository.findByUsername(request.getUsername())
                        .ifPresent(user -> new RuntimeException("Username이 중복됩니다."));

        User savedUser = userRepository.save(request.toEntity());

        return UserDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }
}

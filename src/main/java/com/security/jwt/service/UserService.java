package com.security.jwt.service;

import com.security.jwt.domain.User;
import com.security.jwt.domain.dto.UserDto;
import com.security.jwt.domain.dto.UserJoinRequest;
import com.security.jwt.exception.CustomizedException;
import com.security.jwt.exception.ErrorCode;
import com.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest request) {

        // username 중복 체크 로직
        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    //throw new RuntimeException("Username이 중복됩니다.");
                    throw new CustomizedException(ErrorCode.DUPLICATED_USERNAME,
                            String.format("Username : %s", request.getUsername()));
                });

        // email 중복 체크 로직
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new CustomizedException(ErrorCode.DUPLICATED_EMAIL,
                            String.format("Email : %s", request.getEmail()));
                });

        User savedUser = userRepository.save(request.toEntity());

        return UserDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }
}

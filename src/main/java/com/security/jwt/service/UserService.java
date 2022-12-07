package com.security.jwt.service;

import com.security.jwt.domain.entity.User;
import com.security.jwt.domain.dto.UserDto;
import com.security.jwt.domain.dto.UserJoinRequest;
import com.security.jwt.exception.CustomizedException;
import com.security.jwt.exception.ErrorCode;
import com.security.jwt.repository.UserRepository;
import com.security.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;

    public UserDto join(UserJoinRequest request) {

        // username 중복 체크 로직
        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    //throw new RuntimeException("Username이 중복됩니다.");
                    throw new CustomizedException(ErrorCode.DUPLICATED_USERNAME,
                            String.format("Username : %s", request.getUsername()));
                });

        // 비밀번호를 직접 인코딩해서 전달
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));

        return UserDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .build();
    }

    public String login(String username, String password) {

        // usernme있는지 여부 확인
        // 없으면 NOT_FOUND에러 발생
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomizedException(ErrorCode.NOT_FOUND,
                        String.format("%s는 가입된 적이 없습니다.", username)));

        // password일치 하는지 여부 확인
        if(!encoder.matches(password, user.getPassword())) {
            throw new CustomizedException(ErrorCode.INVALID_PASSWORD,
                    String.format("username 또는 password가 잘못되었습니다."));
        }

        // 두가지 확인중 예외 안 났으면 Token발행
        return JwtTokenUtil.createToken(username, secretKey, expireTimeMs);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomizedException(ErrorCode.NOT_FOUND, "Username Not Found"));
    }
}

package com.security.jwt.controller;

import com.security.jwt.domain.Response;
import com.security.jwt.domain.dto.UserDto;
import com.security.jwt.domain.dto.UserJoinRequest;
import com.security.jwt.domain.dto.UserJoinResponse;
import com.security.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserDto userDto = userService.join(request);
        return Response.success(new UserJoinResponse(userDto.getUsername(), userDto.getEmail()));
    }
}

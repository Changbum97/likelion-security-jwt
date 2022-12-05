package com.security.jwt.controller;

import com.security.jwt.domain.dto.ReviewCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/api/v1/reviews")
@Slf4j
public class ReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest dto, Authentication authentication) {
        log.info("Controller username : {}", authentication.getName());
        return "리뷰 등록에 성공 했습니다.";
    }
}

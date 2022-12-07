package com.security.jwt.controller;

import com.security.jwt.domain.Response;
import com.security.jwt.domain.dto.UserJoinResponse;
import com.security.jwt.domain.dto.VisitCreateRequest;
import com.security.jwt.domain.dto.VisitCreateResponse;
import com.security.jwt.domain.entity.Visit;
import com.security.jwt.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("")
    public Response<VisitCreateResponse> postVisit(@RequestBody VisitCreateRequest request, Authentication authentication) {
        Visit savedVisit = visitService.createVisit(request, authentication.getName());
        return Response.success(VisitCreateResponse.of(savedVisit));
    }

}

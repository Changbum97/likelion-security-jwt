package com.security.jwt.controller;

import com.security.jwt.domain.Response;
import com.security.jwt.domain.dto.VisitCreateRequest;
import com.security.jwt.domain.dto.VisitResponse;
import com.security.jwt.domain.entity.Visit;
import com.security.jwt.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("")
    public Response<VisitResponse> postVisit(@RequestBody VisitCreateRequest request, Authentication authentication) {
        Visit savedVisit = visitService.createVisit(request, authentication.getName());
        return Response.success(VisitResponse.of(savedVisit));
    }

    @GetMapping("")
    public List<VisitResponse> getAllVisit() {
        return visitService.findAll().stream()
                .map(visit -> {
                    return VisitResponse.of(visit);
                }).collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    public List<VisitResponse> getUsersVisit(@PathVariable Long userId) {
        return visitService.findByUserId(userId).stream()
                .map(visit -> {
                    return VisitResponse.of(visit);
                }).collect(Collectors.toList());
    }

    @GetMapping("/hospitals/{hospitalId}")
    public List<VisitResponse> getHospitalsVisit(@PathVariable Long hospitalId) {
        return visitService.findByHospitalId(hospitalId).stream()
                .map(visit -> {
                    return VisitResponse.of(visit);
                }).collect(Collectors.toList());
    }

}

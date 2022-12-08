package com.security.jwt.domain.dto;

import com.security.jwt.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class VisitResponse {

    private Long hospitalId;
    private Long userId;
    private String disease;
    private int medicalExpenses;
    private LocalDateTime createdAt;

    public static VisitResponse of(Visit visit) {
        return VisitResponse.builder()
                .userId(visit.getUser().getId())
                .hospitalId(visit.getHospital().getId())
                .disease(visit.getDisease())
                .medicalExpenses(visit.getMedicalExpenses())
                .createdAt(visit.getCreatedAt())
                .build();
    }
}

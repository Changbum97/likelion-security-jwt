package com.security.jwt.domain.dto;

import com.security.jwt.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class VisitResponse {

    private Long hospitalId;
    private Long userId;
    private String disease;
    private int medicalExpenses;

    public static VisitResponse of(Visit visit) {
        return VisitResponse.builder()
                .userId(visit.getUser().getId())
                .hospitalId(visit.getHospital().getId())
                .disease(visit.getDisease())
                .medicalExpenses(visit.getMedicalExpenses())
                .build();
    }
}

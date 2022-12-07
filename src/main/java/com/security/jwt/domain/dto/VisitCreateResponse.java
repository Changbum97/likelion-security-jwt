package com.security.jwt.domain.dto;

import com.security.jwt.domain.entity.Hospital;
import com.security.jwt.domain.entity.User;
import com.security.jwt.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class VisitCreateResponse {

    private Long hospitalId;
    private Long userId;
    private String disease;
    private int medicalExpenses;

    public static VisitCreateResponse of(Visit visit) {
        return VisitCreateResponse.builder()
                .userId(visit.getUser().getId())
                .hospitalId(visit.getHospital().getId())
                .disease(visit.getDisease())
                .medicalExpenses(visit.getMedicalExpenses())
                .build();
    }
}

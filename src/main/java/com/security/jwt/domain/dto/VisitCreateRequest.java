package com.security.jwt.domain.dto;

import com.security.jwt.domain.entity.Hospital;
import com.security.jwt.domain.entity.User;
import com.security.jwt.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VisitCreateRequest {

    private Long hospitalId;
    private String disease;
    private int medicalExpenses;

    public Visit toEntity(Hospital hospital, User user) {
        return Visit.builder()
                .disease(this.disease)
                .hospital(hospital)
                .user(user)
                .medicalExpenses(this.medicalExpenses)
                .build();
    }
}

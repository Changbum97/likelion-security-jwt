package com.security.jwt.repository;

import com.security.jwt.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByUserId(Long userId);
    List<Visit> findByHospitalId(Long hospitalId);

}

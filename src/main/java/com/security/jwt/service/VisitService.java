package com.security.jwt.service;

import com.security.jwt.domain.dto.VisitCreateRequest;
import com.security.jwt.domain.entity.Hospital;
import com.security.jwt.domain.entity.User;
import com.security.jwt.domain.entity.Visit;
import com.security.jwt.exception.CustomizedException;
import com.security.jwt.exception.ErrorCode;
import com.security.jwt.repository.HospitalRepository;
import com.security.jwt.repository.UserRepository;
import com.security.jwt.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public Visit createVisit(VisitCreateRequest request, String username) {
        // request로 넘어온 hospitalId로 Hospital 찾아오기
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                        .orElseThrow(() -> new CustomizedException(ErrorCode.NOT_FOUND,
                                String.format("hospitalId : %s 가 없습니다.", request.getHospitalId())));

        // Authentication.getName()으로 넘어온 username으로 User 찾아오기
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomizedException(ErrorCode.NOT_FOUND,
                        String.format("username : %s 가 없습니다.", username)));

        Visit visit = visitRepository.save(request.toEntity(hospital, user));
        return visit;
    }

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public List<Visit> findByUserId(Long userId) {
        return visitRepository.findByUserId(userId);
    }

    public List<Visit> findByHospitalId(Long hospitalId) {
        return visitRepository.findByHospitalId(hospitalId);
    }
}

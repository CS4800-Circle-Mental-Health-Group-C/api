package com.circle.api.service;

import org.springframework.stereotype.Service;

import com.circle.api.model.Circle;
import com.circle.api.repository.CircleRepository;
import org.springframework.stereotype.Service;

@Service
public class CircleService {
    
    private CircleRepository circleRepository;

    public CircleService(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;
    }

    public Circle findMemberInfoByIds(String userId, String circleMemberId) {
        return circleRepository.findMemberInfoByIds(userId, circleMemberId);
      }
    
}

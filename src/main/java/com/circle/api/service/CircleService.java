package com.circle.api.service;

import java.util.List;

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

    public Circle getCircleMember(String userId, String email) {
        return circleRepository.getCircleMember(userId, email);
    }

    public List<Circle> getUserCircle(String userId) {
        return circleRepository.getUserCircle(userId);
    }
    
    public Circle addCircleMember(String userId, int circleSize) {
        return circleRepository.addCircleMember(userId,circleSize);
    }
}

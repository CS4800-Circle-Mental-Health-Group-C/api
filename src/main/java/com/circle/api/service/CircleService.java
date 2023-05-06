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

    public Circle getUserCircle(String userId, String circleId) {
        return circleRepository.getUserCircle(userId, circleId);
    }

    public List<Circle> getCircleMembers(String circleId) {
        return circleRepository.getCircleMembers(circleId);
    }
    
}

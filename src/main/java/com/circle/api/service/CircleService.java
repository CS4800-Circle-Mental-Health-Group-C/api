package com.circle.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return circleRepository
                .getCircleMember(userId, email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Circle Member Found"));
    }

    public List<Circle> getUserCircle(String userId) {
        return circleRepository.getUserCircle(userId);
    }
    
    public Circle addCircleMember(String userId,Circle circle,int circleSize) {
        return circleRepository
                .addCircleMember(userId,circle,circleSize)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,"Circle Size Limit Reached"));

    }

    public Circle removeCircleMember(String userId, String email) {
        return circleRepository
                .removeCircleMember(userId, email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Circle Member Found"));
    }

    public Circle updateCircleMember(String userId, Circle circle) {
        return circleRepository.updateCircleMember(userId, circle);
    }
}

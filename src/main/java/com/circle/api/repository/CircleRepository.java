package com.circle.api.repository;

import java.util.List;
import java.util.Optional;

import com.circle.api.model.Circle;

public interface CircleRepository {
    
    public Optional<Circle> getCircleMember(String userId, String email);

    public List<Circle> getUserCircle(String userId);

    public Optional<Circle> addCircleMember(String userId, Circle circle, int circleSize);

    public Optional<Circle> removeCircleMember(String userId, String email);

    public Circle updateCircleMember(String userId, Circle circle);
}

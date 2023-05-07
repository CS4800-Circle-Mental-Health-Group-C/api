package com.circle.api.repository;

import java.util.List;

import com.circle.api.model.Circle;

public interface CircleRepository {
    
    public Circle getCircleMember(String userId, String email);

    public List<Circle> getUserCircle(String userId);

    public Circle addCircleMember(String userId, Circle circle, int circleSize);

    public Circle removeCircleMember(String userId, String email);

    public Circle updateCircleMember(String userId, Circle circle);
}

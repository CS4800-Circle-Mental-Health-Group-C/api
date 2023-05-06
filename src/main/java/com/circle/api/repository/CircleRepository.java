package com.circle.api.repository;

import java.util.List;

import com.circle.api.model.Circle;

public interface CircleRepository {
    
    public Circle getUserCircle(String userId, String circleId);

    public List<Circle> getCircleMembers(String circleId);
}

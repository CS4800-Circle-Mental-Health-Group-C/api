package com.circle.api.controller;

import com.circle.api.model.Circle;

public class CircleMemberRequest {
    public static Circle toCircleMember(Circle circle) {
        return Circle.builder()
                     .email(circle.getEmail()) 
                     .name(circle.getName()) 
                     .phone(circle.getPhone())
                     .userId(circle.getUserId())
                     .circleMemberId(circle.getCircleMemberId())
                     .dateAdded(circle.getDateAdded())
                     .build();
    }
}

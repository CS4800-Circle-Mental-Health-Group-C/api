package com.circle.api.controller;

import com.circle.api.model.Circle;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CircleMemberResponse {
    private String email;
    private String name;
    private String phone;
    private String userId;
    private String circleMemberId;
    private String dateAdded;

    public static CircleMemberResponse from(Circle circle) {
        return CircleMemberResponse.builder()
                                   .email(circle.getEmail()) 
                                   .name(circle.getName()) 
                                   .phone(circle.getPhone())
                                   .userId(circle.getUserId())
                                   .circleMemberId(circle.getCircleMemberId())
                                   .dateAdded(circle.getDateAdded())
                                   .build();
    }

}

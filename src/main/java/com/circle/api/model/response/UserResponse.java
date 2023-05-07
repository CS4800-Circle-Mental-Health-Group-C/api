package com.circle.api.model.response;

import com.circle.api.model.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private String email;
    private String name;
    private String phone;
    private String userId;
    private String dateAdded;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                           .email(user.getEmail())
                           .name(user.getName())
                           .phone(user.getPhone())
                           .userId(user.getUserId())
                           .dateAdded(user.getDateAdded())
                           .build();
        
    }
}

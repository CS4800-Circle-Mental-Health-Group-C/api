package com.circle.api.model.request;

import com.circle.api.model.User;

public class UserRequest {
    
    public static User toUser(User user) {
        return User.builder()
                   .email(user.getEmail())
                   .name(user.getName())
                   .phone(user.getPhone())
                   .userId(user.getUserId())
                   .dateAdded(user.getDateAdded())
                   .build();
    }
}

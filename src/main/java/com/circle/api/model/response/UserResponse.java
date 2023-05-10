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
  private int healthScore;
  private String dateAdded;

  public static UserResponse from(User user) {
    return UserResponse.builder()
        .email(user.getEmail())
        .name(user.getName())
        .phone(user.getPhone())
        .userId(user.getUserId())
        .healthScore(user.getHealthScore())
        .dateAdded(user.getDateAdded())
        .build();
  }
}

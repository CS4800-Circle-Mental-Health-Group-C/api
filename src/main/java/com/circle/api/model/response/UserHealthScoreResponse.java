package com.circle.api.model.response;

import com.circle.api.model.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserHealthScoreResponse {
  private String userId;
  private int healthScore;

  public static UserHealthScoreResponse from(User user) {
    return UserHealthScoreResponse.builder()
        .userId(user.getUserId())
        .healthScore(user.getHealthScore())
        .build();
  }

}

package com.circle.api.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.circle.api.model.Circle;
import com.circle.api.model.Survey;
import com.circle.api.model.User;
import com.circle.api.model.request.UserRequest;
import com.circle.api.model.request.UserSurveyRequest;
import com.circle.api.model.response.CircleMemberResponse;
import com.circle.api.model.response.UserResponse;
import com.circle.api.model.response.UserSurveyResponse;
import com.circle.api.service.CircleService;
import com.circle.api.service.SurveyService;
import com.circle.api.service.UserService;

@Component
public class UserFacade {

  private final UserService userService;
  private final SurveyService surveyService;
  private final CircleService circleService;

  public UserFacade(
      UserService userService, SurveyService surveyService, CircleService circleService) {
    this.userService = userService;
    this.surveyService = surveyService;
    this.circleService = circleService;
  }

  public UserResponse findById(String id) {
    User user = userService.findById(id);
    return UserResponse.from(user);
  }

  public UserResponse createUser(User user) {
    User userToCreate = UserRequest.toUser(user);
    User createdUser = userService.createUser(userToCreate);
    return UserResponse.from(createdUser);
  }

  public List<UserSurveyResponse> findAllUserSurveys(String id) {
    return surveyService.findAllUserSurveys(id).stream()
        .map(UserSurveyResponse::from)
        .collect(Collectors.toList());
  }

  public UserSurveyResponse findUserSurvey(String userId, String surveyId) {
    Survey survey = surveyService.findUserSurvey(userId, surveyId);
    return UserSurveyResponse.from(survey);
  }

  public UserSurveyResponse createUserSurvey(String id, Survey survey) {
    Survey userSurveyToCreate = UserSurveyRequest.toUserSurvey(survey);
    Survey createdUserSurvey = surveyService.createUserSurvey(id, userSurveyToCreate);
    return UserSurveyResponse.from(createdUserSurvey);
  }

  public CircleMemberResponse getCircleMember(String userId, String email) {
    Circle circle = circleService.getCircleMember(userId, email);
    return CircleMemberResponse.from(circle);
  }

    public List<CircleMemberResponse> getUserCircle(String userId) {
        List<Circle> userCircle = circleService.getUserCircle(userId);
        return userCircle.stream().map(CircleMemberResponse::from).collect(Collectors.toList());
    }
    
    public CircleMemberResponse addCircleMember(String userId, Circle circle, int circleSize) {
        Circle circleMember = circleService.addCircleMember(userId,circle,circleSize);
        return CircleMemberResponse.from(circleMember);
    }

    public CircleMemberResponse removeCircleMember(String userId, String email) {
        Circle circleMember = circleService.removeCircleMember(userId, email);
        return CircleMemberResponse.from(circleMember);
    }

    public CircleMemberResponse updateCircleMember(String userId, Circle circle) {
        Circle updatedCircleMember = circleService.updateCircleMember(userId, circle);
        return CircleMemberResponse.from(updatedCircleMember);
    }
}

package com.circle.api.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.circle.api.controller.CircleMemberResponse;
import com.circle.api.controller.UserRequest;
import com.circle.api.controller.UserResponse;
import com.circle.api.controller.UserSurveyResponse;
import com.circle.api.controller.UserSurveyRequest;
import com.circle.api.model.Circle;
import com.circle.api.model.Survey;
import com.circle.api.model.User;
import com.circle.api.service.CircleService;
import com.circle.api.service.SurveyService;
import com.circle.api.service.UserService;

@Component
public class UserFacade {

    private final UserService userService; 
    private final SurveyService surveyService;
    private final CircleService circleService;

    public UserFacade(UserService userService, SurveyService surveyService, CircleService circleService)  {
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
        return surveyService.findAllUserSurveys(id)
                            .stream()
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
    
    public CircleMemberResponse getUserCircle(String userId, String circleId) {
        Circle circle = circleService.getUserCircle(userId, circleId);
        return CircleMemberResponse.from(circle);
    }
    
}

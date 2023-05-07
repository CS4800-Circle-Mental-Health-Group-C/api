package com.circle.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.circle.api.facade.UserFacade;
import com.circle.api.model.Circle;
import com.circle.api.model.Survey;
import com.circle.api.model.User;
import com.circle.api.model.response.CircleMemberResponse;
import com.circle.api.model.response.UserResponse;
import com.circle.api.model.response.UserSurveyResponse;

@RestController
@EnableWebMvc
public class UserController {

  private UserFacade userFacade;
  private Logger logger;

  UserController(UserFacade userFacade) {
    this.userFacade = userFacade;
    this.logger = LoggerFactory.getLogger(UserController.class);
  }

  // GET a user by ID
  @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
  public UserResponse getUser(@PathVariable("id") String id) {
    logger.info("Getting USER#: " + id);

    UserResponse userResponse = userFacade.findById(id);

    logger.info("User: " + userResponse);

    return userResponse;
  }

  // POST a user using the RequestBody
  @RequestMapping(path = "/user", method = RequestMethod.POST)
  public UserResponse createUser(@RequestBody User user) {
    logger.info("Creating user...");

    return userFacade.createUser(user);
  }

  // GET all surveys taken by a user
  @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.GET)
  public List<UserSurveyResponse> findAllUserSurveys(@PathVariable("id") String id) {

    logger.info("Getting user surveys");

    return userFacade.findAllUserSurveys(id);
  }

  // GET a specific survey taken by a user
  @RequestMapping(path = "/user/{uid}/survey/{sid}", method = RequestMethod.GET)
  public UserSurveyResponse findUserSurvey(
      @PathVariable("uid") String userId, @PathVariable("sid") String surveyId) {

    logger.info("Getting USER#" + userId + " SURVEY#" + surveyId);

    return userFacade.findUserSurvey(userId, surveyId);
  }

  // POST a survey response taken by a user
  @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.POST)
  public UserSurveyResponse createUserSurvey(
      @PathVariable("id") String id, @RequestBody Survey survey) {

    logger.info("Creating SURVEY#" + survey.getSurveyFormId() + " response under USER#" + id);

    return userFacade.createUserSurvey(id, survey);
  }

  // GET info from a specific circle member in a user circle
  @RequestMapping(path = "/user/{uid}/circle/{email}", method = RequestMethod.GET)
  public CircleMemberResponse getCircleMember(
      @PathVariable("uid") String userId, @PathVariable("email") String email) {
    logger.info("Getting Circle Member Info");

    CircleMemberResponse circleMember = userFacade.getCircleMember(userId, email);

    logger.info("Member Info: " + circleMember);

    return circleMember;
  }

  // GET info from all members in a user circle
  @RequestMapping(path = "/user/{uid}/circle", method = RequestMethod.GET)
  public List<CircleMemberResponse> getUserCircle(@PathVariable("uid") String userId) {
    logger.info("Getting Circle Members from USER#" + userId + " Circle");

    List<CircleMemberResponse> circleMembers = userFacade.getUserCircle(userId);

    return circleMembers;
  }

  // POST a new member in the user circle (if circle size < 5 and member.email does not exist)
  @RequestMapping(path = "/user/{uid}/circle", method = RequestMethod.POST)
  public CircleMemberResponse addCircleMember(
      @PathVariable("uid") String userId, @RequestBody Circle circle) {
    logger.info("Create Circle Member");

    return userFacade.addCircleMember(userId, circle);
  }
}
// - /user/{id}                  (GET - gets a user)
// - /user                       (POST - creates a new user)
// - /user/{id}/survey           (GET - gets a specified user's surveys)
// - /user/{id}/survey           (POST - add/create survey response for a user)
// - /user/{id}/circle/{email}   (GET - gets a specific user circle member)
// - /user/{id}/circle           (GET - gets all user circle members)
// - /user/{id}/circle           (POST - add a member to user circle)
// - /user/{id}/circle           (PATCH - update a member in the user circle)

// - /user/{id}/survey/date=?queryParam (GET - get a survey at a specific date)

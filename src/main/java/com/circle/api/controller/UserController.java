package com.circle.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.circle.api.model.Survey;
import com.circle.api.model.User;
import com.circle.api.service.SurveyService;
import com.circle.api.service.UserService;

@RestController
@EnableWebMvc
public class UserController {

  private UserService userService;
  private SurveyService surveyService;
  private Logger logger;

  UserController(UserService userService, SurveyService surveyService) {
    this.userService = userService;
    this.surveyService = surveyService;
    this.logger = LoggerFactory.getLogger(UserController.class);
  }

  @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
  public User getUser(@PathVariable("id") String id) {
    logger.info("Getting user: " + id);

    User user = userService.findById(id);

    logger.info("User: " + user);

    return user;
  }

  @RequestMapping(path = "/user", method = RequestMethod.POST)
  public User createUser(@RequestBody User user) {
    logger.info("Creating user: " + user);

    userService.createUser(user);

    return user;
  }

  @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.GET) 
  public List<Survey> findAllUserSurveys(@PathVariable("id") String id) {

    logger.info("Getting user surveys");

    return surveyService.findAllUserSurveys(id);

  }

  @RequestMapping(path = "/user/{uid}/survey/{sid}", method = RequestMethod.GET) 
  public Survey findUserSurvey(@PathVariable("uid") String userId, @PathVariable("sid") String surveyId) {

    logger.info("Getting USER#" + userId + " SURVEY#" + surveyId);

    return surveyService.findUserSurvey(userId,surveyId);

  }

  @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.POST) 
  public Survey createUserSurvey(@PathVariable("id") String id, @RequestBody Survey survey) {

    logger.info("Creating SURVEY#" + id + " response under USER#" + survey.getUserId());

    return surveyService.createUserSurvey(id,survey);

  }


  

}
// - /user?id=queryParam (GET - gets a user) 
// - /user               (POST - creates a new user)
// - /user/{id}/survey   (GET - gets a specified user's surveys)
// - /user/{id}/survey   (POST - add/create survey response for a user)
// - /user/{id}/circle   (GET - gets a user's circle members)
// - /user/{id}/circle   (POST - add/create user's circle members)
// - /user/{id}/circle   (PATCH - updates a user's circle members)

// - /user/{id}/survey/date=?queryParam (GET - get a survey at a specific date)

      
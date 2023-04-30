package com.circle.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.circle.api.model.User;
import com.circle.api.service.UserService;

@RestController
@EnableWebMvc
public class UserController {

  private UserService userService;
  private Logger logger;

  UserController(UserService userService) {
    this.userService = userService;
    this.logger = LoggerFactory.getLogger(UserController.class);
  }

  @RequestMapping(path = "/user", method = RequestMethod.GET)
  public User getUser(@RequestParam String id) {
    logger.info("Getting user: " + id);

    User user = userService.findById(id);

    logger.info("User: " + user);

    return user;
  }
}






  // @RequestMapping(path = "/user", method = RequestMethod.POST)
  // public User createUser(@RequestBody User user) {
  //   logger.info("Creating user: " + user);

  //   userService.createUser(user);
  //   return user;
  // }

// x user/ with a POST method to create a new user
// x user/id=?queryParam or user/pathParam with a GET to get a user
// user/{id}/circle with GET to return a users circle members
// user/{id}/survey with a POST to create a survey response for a user
// user/{id}/circle with POST and PATCH to create and update circle members
// user/{id}/survey/date=?queryParam with a GET to return a survey for a specific date
// since everything is really based around a single user
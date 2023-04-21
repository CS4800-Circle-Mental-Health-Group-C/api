package com.circle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.circle.model.User;
import com.circle.service.UserService;

@RestController
@EnableWebMvc
public class UserController {

  private UserService userService;
  private Logger logger;

  UserController(UserService userService) {
    this.userService = userService;
    this.logger = LoggerFactory.getLogger(UserController.class);
  }

  @RequestMapping(path = "/user", method = RequestMethod.POST)
  public User createUser(@RequestBody User user) {
    logger.info("Creating user: " + user);

    userService.createUser(user);
    return user;
  }

  @RequestMapping(path = "/user", method = RequestMethod.GET)
  public User getUser(@RequestParam String id) {
    logger.info("Getting user: " + id);

    User user = userService.getUser(id);

    logger.info("User: " + user);

    return user;
  }
}

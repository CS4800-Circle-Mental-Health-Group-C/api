package com.circle.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.circle.api.model.Circle;
import com.circle.api.service.CircleService;

@RestController
public class CircleController {
    
  private CircleService circleService;
  private Logger logger;

  CircleController(CircleService circleService) {
    this.circleService = circleService;
    this.logger = LoggerFactory.getLogger(CircleController.class);
  }

  @RequestMapping(path = "/user/circle", method = RequestMethod.GET)
  public Circle getCircleMember(@RequestParam String userId, @RequestParam String circleMemberId) {
    logger.info("Getting Circle Member Info");

    Circle circle = circleService.findMemberInfoByIds(userId,circleMemberId);

    logger.info("Circle Member Info: " + circle);

    return circle;
  }

}

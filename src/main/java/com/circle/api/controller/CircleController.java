package com.circle.api.controller;

import java.util.List;

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

  
  // @RequestMapping(path = "/circle/{id}", method = RequestMethod.GET)
  // public List<Circle> getCircleMembers(@PathVariable("id") String circleId) {
  //   logger.info("Getting Circle Members for CIRCLE#" + circleId);

  //   List<Circle> circleMembers = circleService.getCircleMembers(circleId);

  //   return circleMembers;
  // }

}

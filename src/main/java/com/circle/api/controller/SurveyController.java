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

import com.circle.api.model.Survey;
import com.circle.api.service.SurveyService;

@RestController
public class SurveyController {
    
  private SurveyService surveyService;
  private Logger logger;

  SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
    this.logger = LoggerFactory.getLogger(SurveyController.class);
  }

  // @RequestMapping(path = "/user/survey", method = RequestMethod.GET)
  // public Survey getSurvey(@RequestParam String userId, @RequestParam String surveyId) {
  //   logger.info("Getting Survey: " + surveyId);

  //   Survey survey = surveyService.findByIds(userId,surveyId);

  //   logger.info("Survey: " + survey);

  //   return survey;
  // }

  // @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.POST)
  // @ResponseBody
  // public Survey getSurvey(@PathVariable("id") String id, @RequestBody Survey survey) {
  //   logger.info("Adding survey: " + survey);
  //   survey.setKey(id);
  //   survey.setUserId(id);
  //   surveyService.addSurvey(survey);
  //   return survey;
  // }
}

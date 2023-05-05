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
import com.circle.api.facade.SurveyFacade;

@RestController
public class SurveyController {
  
  private SurveyFacade surveyFacade;
  private SurveyService surveyService;
  private Logger logger;

  SurveyController(SurveyService surveyService, SurveyFacade surveyFacade) {
    this.surveyService = surveyService;
    this.surveyFacade = surveyFacade;
    this.logger = LoggerFactory.getLogger(SurveyController.class);
  }

  @RequestMapping(path = "/survey/{sid}", method = RequestMethod.GET)
  public SurveyResponse findSurveyById(@PathVariable("sid") String surveyId) {
    logger.info("Getting SURVEY#" + surveyId);

    SurveyResponse surveyResponse = surveyFacade.findSurveyById(surveyId);

    logger.info("Survey: " + surveyResponse);

    return surveyResponse;
  }

  @RequestMapping(path = "/survey", method = RequestMethod.POST)
  public SurveyResponse addSurvey(@RequestBody Survey survey) {
    
    SurveyResponse surveyResponse = surveyFacade.addSurvey(survey);

    logger.info("Adding survey: " + surveyResponse);
    
    return surveyResponse;
  }
}

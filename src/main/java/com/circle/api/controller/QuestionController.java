package com.circle.api.controller;

import com.circle.api.model.Question;
import com.circle.api.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    
    private QuestionService questionService;
    private Logger logger;
  
    QuestionController(QuestionService questionService) {
      this.questionService = questionService;
      this.logger = LoggerFactory.getLogger(QuestionController.class);
    }

    @RequestMapping(path = "/survey", method = RequestMethod.GET) 
    public Question getQuestionFromSurvey(@RequestParam String surveyId, @RequestParam String questionId)
    {
        logger.info("Getting question: " + questionId);

        Question question = questionService.findByIds(surveyId,questionId);

        logger.info("Question: " + question);

        return question;
    }
}

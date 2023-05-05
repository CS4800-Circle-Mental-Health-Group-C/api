package com.circle.api.facade;

import org.springframework.stereotype.Component;

import com.circle.api.controller.SurveyRequest;
import com.circle.api.controller.SurveyResponse;
import com.circle.api.model.Survey;
import com.circle.api.service.SurveyService;



@Component
public class SurveyFacade {
    
    private final SurveyService surveyService;

    public SurveyFacade(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public SurveyResponse findSurveyById(String surveyId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        return SurveyResponse.from(survey);
    }
    
    public SurveyResponse addSurvey(Survey survey) {
        Survey surveyToCreate = SurveyRequest.toSurvey(survey);
        Survey createdSurvey = surveyService.addSurvey(surveyToCreate);
        return SurveyResponse.from(createdSurvey);
    }


}

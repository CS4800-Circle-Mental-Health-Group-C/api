package com.circle.api.service;

import com.circle.api.model.Survey;

import java.util.List;

import org.springframework.stereotype.Service;
import com.circle.api.repository.SurveyRepository;

@Service
public class SurveyService {
    
    private SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey findSurveyById(String surveyId) {
        return surveyRepository.findSurveyById(surveyId);
    }

    public Survey addSurvey(Survey survey) {
        survey.setPartitionKey(Survey.SURVEY_PK_PREFIX + survey.getSurveyFormId());
        survey.setSortKey(Survey.SURVEY_SK_PREFIX + survey.getSurveyFormId());
        return surveyRepository.addSurvey(survey);
    }

    public List<Survey> findAllUserSurveys(String id) {
        return surveyRepository.findAllUserSurveys(id);
    }

    public Survey findUserSurvey(String userId, String surveyId) {
        return surveyRepository.findUserSurvey(userId,surveyId);
    }

    public Survey createUserSurvey(String id, Survey survey) {
        return surveyRepository.createUserSurvey(id, survey);
    }

}

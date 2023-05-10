package com.circle.api.service;

import com.circle.api.model.Survey;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.circle.api.repository.SurveyRepository;

@Service
public class SurveyService {
    
    private SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey findSurveyById(String surveyId) {
        return surveyRepository
                .findSurveyById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Survey Found"));
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
        return surveyRepository
                .findUserSurvey(userId,surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No User Survey Found"));
    }

    public Survey createUserSurvey(String id, Survey survey) {
        return surveyRepository.createUserSurvey(id, survey);
    }

    public Survey deleteUserSurvey(String userId, String surveyId) {
        return surveyRepository
                .deleteUserSurvey(userId, surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No User Survey Found"));
    }

}

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

    public Survey findByIds(String userId, String surveyId) {
        return surveyRepository.findByUserIdSurveyId(userId,surveyId);
    }

    public Survey addSurvey(Survey survey) {
        return surveyRepository.addSurvey(survey);
    }

    public List<Survey> findAllUserSurveys(String id) {
        return surveyRepository.findAllUserSurveys(id);
    }

    public Survey findUserSurvey(String userId, String surveyId) {
        return surveyRepository.findUserSurvey(userId,surveyId);
    }

}

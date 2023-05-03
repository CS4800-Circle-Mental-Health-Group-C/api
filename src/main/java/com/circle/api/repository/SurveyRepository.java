package com.circle.api.repository;

import java.util.List;

import com.circle.api.model.Survey;

public interface SurveyRepository {

    public Survey findByUserIdSurveyId(String userId, String surveyId);
    
    public Survey addSurvey(Survey survey);

    public List<Survey> findAllUserSurveys(String id);
    
    public Survey findUserSurvey(String userId, String surveyId);

    public Survey createUserSurvey(String id, Survey survey);

}

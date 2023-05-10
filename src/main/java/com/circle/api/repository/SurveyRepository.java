package com.circle.api.repository;

import java.util.List;
import java.util.Optional;

import com.circle.api.model.Survey;

public interface SurveyRepository {

    public Optional<Survey> findSurveyById(String surveyId);
    
    public Survey addSurvey(Survey survey);

    public List<Survey> findAllUserSurveys(String id);
    
    public Optional<Survey> findUserSurvey(String userId, String surveyId);

    public Survey createUserSurvey(String id, Survey survey);

    public Optional<Survey> deleteUserSurvey(String userId, String surveyId);

}

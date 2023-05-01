package com.circle.api.repository;

import com.circle.api.model.Survey;

public interface SurveyRepository {

    public Survey findByUserIdSurveyId(String userId, String surveyId);
    
}

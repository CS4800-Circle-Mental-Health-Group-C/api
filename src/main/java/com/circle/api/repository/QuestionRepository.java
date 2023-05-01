package com.circle.api.repository;

import com.circle.api.model.Question;



public interface QuestionRepository {
    
    Question findBySurveyIdQuestionId(String surveyId, String questionId);

}

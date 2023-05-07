package com.circle.api.model.request;

import com.circle.api.model.Survey;

public class SurveyRequest {
    public static Survey toSurvey(Survey survey) {
        return Survey.builder()
                     .surveyFormId(survey.getSurveyFormId())
                     .surveyQuestions(survey.getSurveyQuestions())
                     .surveyAnswers(survey.getSurveyAnswers())
                     .build();
                    
    }
}

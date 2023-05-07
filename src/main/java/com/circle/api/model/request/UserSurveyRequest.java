package com.circle.api.model.request;

import com.circle.api.model.Survey;

public class UserSurveyRequest { 
    public static Survey toUserSurvey(Survey survey) {
        return Survey.builder()
                     .userId(survey.getUserId())
                     .surveyFormId(survey.getSurveyFormId())
                     .dateTaken(survey.getDateTaken())
                     .surveyResponses(survey.getSurveyResponses())
                     .build();          
    }
}

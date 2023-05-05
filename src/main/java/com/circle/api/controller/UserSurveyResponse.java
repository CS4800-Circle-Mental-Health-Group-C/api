package com.circle.api.controller;

import java.util.Map;

import com.circle.api.model.Survey;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSurveyResponse {
    private String userId; 
    private String surveyFormId;
    private String dateTaken;
    private Map<String,String> surveyResponses;

    public static UserSurveyResponse from(Survey survey) {
        return UserSurveyResponse.builder()
                                 .userId(survey.getUserId())
                                 .surveyFormId(survey.getSurveyFormId())
                                 .dateTaken(survey.getDateTaken())
                                 .surveyResponses(survey.getSurveyResponses())
                                 .build();          
    }
}

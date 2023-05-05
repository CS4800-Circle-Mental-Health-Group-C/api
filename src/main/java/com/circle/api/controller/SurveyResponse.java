package com.circle.api.controller;


import java.util.List;

import com.circle.api.model.Survey;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SurveyResponse {
    private String surveyFormId;
    private List<String> surveyQuestions;
    private List<String> surveyAnswers; 

    public static SurveyResponse from(Survey survey) {
        return SurveyResponse.builder()
                             .surveyFormId(survey.getSurveyFormId()) 
                             .surveyQuestions(survey.getSurveyQuestions())
                             .surveyAnswers(survey.getSurveyAnswers())
                             .build();
    }
}

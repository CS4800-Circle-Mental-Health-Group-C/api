package com.circle.api.repository;

import com.circle.api.model.Survey;
import org.springframework.stereotype.Repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
public class DynamoDbSurveyRepository implements SurveyRepository {

    private final DynamoDbTable<Survey> surveyTable;

    public DynamoDbSurveyRepository(DynamoDbTable<Survey> surveyTable) {
        this.surveyTable = surveyTable;
    }

    @Override
    public Survey findByUserIdSurveyId(String userId, String surveyId) {
        Key key = Key.builder()
                     .partitionValue(Survey.SurveyKeyBuilder.makePartitionKey(userId))
                     .sortValue(Survey.SurveyKeyBuilder.makeSortKey(surveyId))
                     .build();
        Survey survey = surveyTable.getItem(key);
        return survey;
    }

    @Override 
    public Survey addSurvey(Survey survey) {
        surveyTable.putItem(survey);
        return survey;
    }

    
}

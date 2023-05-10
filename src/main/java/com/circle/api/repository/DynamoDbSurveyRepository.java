package com.circle.api.repository;

import com.circle.api.model.Survey;
import com.circle.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class DynamoDbSurveyRepository implements SurveyRepository {

    private final DynamoDbTable<Survey> surveyTable;

    public DynamoDbSurveyRepository(DynamoDbTable<Survey> surveyTable) {
        this.surveyTable = surveyTable;
    }

    @Override
    public Optional<Survey> findSurveyById(String surveyId) {
        Key key = Key.builder()
                     .partitionValue(Survey.SurveyKeyBuilder.makePartitionKey(surveyId))
                     .sortValue(Survey.SurveyKeyBuilder.makeSortKey(surveyId))
                     .build();
        Survey survey = surveyTable.getItem(key);
        return Optional.ofNullable(survey);
    }

    @Override 
    public Survey addSurvey(Survey survey) {
        surveyTable.putItem(survey);
        return survey;
    }

    @Override 
    public List<Survey> findAllUserSurveys(String id) {
        QueryConditional skBeginsWithQuery = QueryConditional.sortBeginsWith(
            Key.builder()
               .partitionValue(User.USER_PK_PREFIX + id)
               .sortValue(Survey.SURVEY_SK_PREFIX)
               .build()
        );

        return surveyTable.query(skBeginsWithQuery)
                          .items()
                          .stream()
                          .collect(Collectors.toList());
    }

    @Override
    public Optional<Survey> findUserSurvey(String userId, String surveyId) {
        Key key = Key.builder()
                     .partitionValue(User.USER_PK_PREFIX + userId)
                     .sortValue(Survey.SURVEY_SK_PREFIX + surveyId)
                     .build();
        Survey survey = surveyTable.getItem(key);
        return Optional.ofNullable(survey);
    }

    @Override
    public Survey createUserSurvey(String id, Survey survey) {
        survey.setUserId(id);
        survey.setPartitionKey(User.USER_PK_PREFIX + id);
        survey.setSortKey(Survey.SURVEY_SK_PREFIX + survey.getSurveyFormId());
        surveyTable.putItem(survey);
        return survey;
    }

    @Override
    public Optional<Survey> deleteUserSurvey(String userId, String surveyId) {
        Key key = 
          Key.builder()
             .partitionValue(User.USER_PK_PREFIX + userId)
             .sortValue(Survey.SURVEY_SK_PREFIX + surveyId)
             .build();   

        return Optional.ofNullable(surveyTable.deleteItem(key));
    }

}

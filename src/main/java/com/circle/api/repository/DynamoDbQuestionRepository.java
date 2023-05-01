package com.circle.api.repository;

import com.circle.api.model.Question;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbQuestionRepository implements QuestionRepository {

    private final DynamoDbTable<Question> questionTable;

    public DynamoDbQuestionRepository(DynamoDbTable<Question> questionTable) {
        this.questionTable = questionTable;
    }

    @Override
    public Question findBySurveyIdQuestionId(String surveyId, String questionId) {
        Key key = Key.builder()
                     .partitionValue(Question.QuestionKeyBuilder.makePartitionKey(surveyId))
                     .sortValue(Question.QuestionKeyBuilder.makeSortKey(questionId))
                     .build();

        Question question = questionTable.getItem(key);
        
        return question;
    }
    
}

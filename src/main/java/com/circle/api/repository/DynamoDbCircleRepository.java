package com.circle.api.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import com.circle.api.model.Circle;
import com.circle.api.model.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Repository
public class DynamoDbCircleRepository implements CircleRepository{


    private final DynamoDbTable<Circle> circleTable;

    public DynamoDbCircleRepository(DynamoDbTable<Circle> circleTable) {
        this.circleTable = circleTable;
    }

    @Override
    public Circle getCircleMember(String userId, String email) {
        Key key = Key.builder()
                     .partitionValue(User.USER_PK_PREFIX + userId)
                     .sortValue(Circle.CIRCLE_SK_PREFIX + email)
                     .build();
                     
        Circle circle = circleTable.getItem(key);
        return circle;
    }

    @Override
    public List<Circle> getUserCircle(String userId) {
       
        QueryConditional skBeginsWithQuery = QueryConditional.sortBeginsWith(
            Key.builder()
               .partitionValue(User.USER_PK_PREFIX + userId)
               .sortValue(Circle.CIRCLE_SK_PREFIX)
               .build()
        );

        return circleTable.query(skBeginsWithQuery)
                          .items()
                          .stream()
                          .collect(Collectors.toList());
    }

    // Functional? Needs More Testing
    @Override
    public Circle addCircleMember(String userId, Circle circle, int circleSize) {
        if(circleSize >= 5) {
            // Placeholder
            return new Circle();
        }
        else 
        {   
            circle.setCircleMemberId(circle.getCircleMemberId());
            circle.setUserId(userId);
            circle.setPartitionKey(User.USER_PK_PREFIX + userId);
            circle.setSortKey(Circle.CIRCLE_SK_PREFIX + circle.getEmail());

            AttributeValue att = AttributeValue.builder()
                                               .s(circle.getEmail())
                                               .build();

            // Not entirely how this works... Or if it really compares the emails...
            Expression myexp = Expression.builder() 
                                         .expression("#email <> :email")
                                         .putExpressionName("#email","Email")
                                         .putExpressionValue(":email", att)
                                         .build();

            PutItemEnhancedRequest<Circle> enhancedRequest = 
                                    PutItemEnhancedRequest.builder(Circle.class)
                                                          .conditionExpression(myexp)
                                                          .item(circle)
                                                          .build();
            
            circleTable.putItem(enhancedRequest);
            return circle;  
        }
    }
    
}

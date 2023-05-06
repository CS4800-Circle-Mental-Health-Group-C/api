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
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

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

    @Override
    public Circle addCircleMember(String userId, int circleSize) {
        if(circleSize >= 5) {
            return new Circle();
        }
        else 
        {
            Expression myexp = Expression.builder()
                                         .expression("")
                                         .putExpressionName("","")
                                         //.putExpressionValue("")
                                         .build();
        }
        return null;
    }
    
}

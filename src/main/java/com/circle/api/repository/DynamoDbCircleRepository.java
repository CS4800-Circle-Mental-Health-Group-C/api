package com.circle.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.circle.api.model.Circle;
import com.circle.api.model.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
public class DynamoDbCircleRepository implements CircleRepository{


    private final DynamoDbTable<Circle> circleTable;

    public DynamoDbCircleRepository(DynamoDbTable<Circle> circleTable) {
        this.circleTable = circleTable;
    }

    @Override
    public Circle getUserCircle(String userId, String circleId) {
        Key key = Key.builder()
                     .partitionValue(User.USER_PK_PREFIX + userId)
                     .sortValue(Circle.CIRCLE_SK_PREFIX + circleId)
                     .build();
                     
        Circle circle = circleTable.getItem(key);
        return circle;
    }

    @Override
    public List<Circle> getCircleMembers(String circleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCircleMembers'");
    }
    
}

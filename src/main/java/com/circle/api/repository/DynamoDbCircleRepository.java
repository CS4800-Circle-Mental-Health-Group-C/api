package com.circle.api.repository;

import org.springframework.stereotype.Repository;
import com.circle.api.model.Circle;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
public class DynamoDbCircleRepository implements CircleRepository{


    private final DynamoDbTable<Circle> circleTable;

    public DynamoDbCircleRepository(DynamoDbTable<Circle> circleTable) {
        this.circleTable = circleTable;
    }

    @Override
    public Circle findMemberInfoByIds(String userId, String circleMemberId) {
        Key key = Key.builder()
                     .partitionValue(Circle.CircleKeyBuilder.makePartitionKey(userId))
                     .sortValue(Circle.CircleKeyBuilder.makeSortKey(circleMemberId))
                     .build();
        Circle circle = circleTable.getItem(key);
        return circle;
    }
    
}

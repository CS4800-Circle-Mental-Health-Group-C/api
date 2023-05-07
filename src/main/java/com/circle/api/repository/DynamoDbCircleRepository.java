package com.circle.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.LimitExceededException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.circle.api.model.Circle;
import com.circle.api.model.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class DynamoDbCircleRepository implements CircleRepository {

  private final DynamoDbTable<Circle> circleTable;

  public DynamoDbCircleRepository(DynamoDbTable<Circle> circleTable) {
    this.circleTable = circleTable;
  }

  @Override
  public Optional<Circle> getCircleMember(String userId, String email) {
    Key key =
        Key.builder()
            .partitionValue(User.USER_PK_PREFIX + userId)
            .sortValue(Circle.CIRCLE_SK_PREFIX + email)
            .build();

    Circle circle = circleTable.getItem(key);
    return Optional.ofNullable(circle);
  }

  @Override
  public List<Circle> getUserCircle(String userId) {

    QueryConditional skBeginsWithQuery =
        QueryConditional.sortBeginsWith(
            Key.builder()
                .partitionValue(User.USER_PK_PREFIX + userId)
                .sortValue(Circle.CIRCLE_SK_PREFIX)
                .build());

    return circleTable.query(skBeginsWithQuery).items().stream().collect(Collectors.toList());
  }

    @Override
    public Optional<Circle> addCircleMember(String userId, Circle circle, int circleSize) {
      
      if(circleSize >= 5) {
        return Optional.empty();
      }
      
      circle.setCircleMemberId(circle.getCircleMemberId());
      circle.setUserId(userId);
      circle.setPartitionKey(User.USER_PK_PREFIX + userId);
      circle.setSortKey(Circle.CIRCLE_SK_PREFIX + circle.getEmail());

      AttributeValue att = AttributeValue.builder().s(circle.getEmail()).build();

      Expression myexp =
          Expression.builder()
              .expression("#email <> :email")
              .putExpressionName("#email", "Email")
              .putExpressionValue(":email", att)
              .build();

            PutItemEnhancedRequest<Circle> enhancedRequest = 
                                    PutItemEnhancedRequest.builder(Circle.class)
                                                          .conditionExpression(myexp)
                                                          .item(circle)
                                                          .build();
            
      try { 
        circleTable.putItem(enhancedRequest);
      }
      catch(RuntimeException re) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Circle Member Already Added");
      }

      return getCircleMember(userId,circle.getEmail());  
    }
    
    @Override
    public Optional<Circle> removeCircleMember(String userId, String email) {

      Key key = 
          Key.builder()
             .partitionValue(User.USER_PK_PREFIX + userId)
             .sortValue(Circle.CIRCLE_SK_PREFIX + email)
             .build();
      
      AttributeValue att = AttributeValue.builder().s(email).build();

      Expression myexp = 
          Expression.builder() 
                    .expression("#email = :email")
                    .putExpressionName("#email","Email")
                    .putExpressionValue(":email", att)
                    .build();
          
      DeleteItemEnhancedRequest enhancedRequest =
              DeleteItemEnhancedRequest.builder()
                                       .conditionExpression(myexp)
                                       .key(key)
                                       .build();

      return Optional.ofNullable(circleTable.deleteItem(enhancedRequest));  
    }

    @Override
    public Circle updateCircleMember(String userId, Circle circle) {

      circle.setUserId(userId);
      circle.setPartitionKey(User.USER_PK_PREFIX + userId);
      circle.setSortKey(Circle.CIRCLE_SK_PREFIX + circle.getEmail());
      
      AttributeValue att = 
          AttributeValue.builder()
                        .s(circle.getEmail())
                        .build();

      Expression myexp = 
          Expression.builder() 
                    .expression("#email = :email")
                    .putExpressionName("#email","Email")
                    .putExpressionValue(":email", att)
                    .build();

      PutItemEnhancedRequest<Circle> enhancedRequest =
          PutItemEnhancedRequest.builder(Circle.class)
                                .item(circle)
                                .conditionExpression(myexp)
                                .build();

      circleTable.putItem(enhancedRequest);
      return circle;
    }
}

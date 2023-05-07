package com.circle.api.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.circle.api.model.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
public class DynamoDbUserRepository implements UserRepository {

  private final DynamoDbTable<User> userTable;

  public DynamoDbUserRepository(DynamoDbTable<User> userTable) {
    this.userTable = userTable;
  }

  @Override
  public Optional<User> findByUserId(String id) {
    Key key =
        Key.builder()
            .partitionValue(User.UserKeyBuilder.makePartitionKey(id))
            .sortValue(User.UserKeyBuilder.makeSortKey(id))
            .build();
    return Optional.ofNullable(userTable.getItem(key));
  }

  @Override
  public User createUser(User user) {
    userTable.putItem(user);
    return user;
  }
}

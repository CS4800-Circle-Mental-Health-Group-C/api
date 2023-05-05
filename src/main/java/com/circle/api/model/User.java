package com.circle.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class User extends DynamoDbBase{

  public static final String USER_PK_PREFIX = "USER#";
  public static final String USER_SK_PREFIX = "USER#";

  private String email;
  private String name;
  private String phone;
  private String userId;
  private String dateAdded;

  public User() {}

  public User(UserBuilder userBuilder) {
    this.email = userBuilder.email; 
    this.name = userBuilder.name;
    this.phone = userBuilder.phone;
    this.userId = userBuilder.userId;
    this.dateAdded = userBuilder.dateAdded;
    this.partitionKey = userBuilder.partitionKey;
    this.sortKey = userBuilder.sortKey;
  }
  
  public void setKey(String id) {
    setPartitionKey(UserKeyBuilder.makePartitionKey(id));
    setSortKey(UserKeyBuilder.makeSortKey(id));
  }

  // Getters and Setters
  @DynamoDbAttribute("Email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @DynamoDbAttribute("Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @DynamoDbAttribute("Phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @DynamoDbAttribute("User Id")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @DynamoDbAttribute("Date Added")
  public String getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(String dateAdded) {
    this.dateAdded = dateAdded;
  }
 
  // Return UserBuilder Object
  public static UserBuilder builder() {
    return new UserBuilder();
  }

  public static class UserBuilder {

    private String email;
    private String name;
    private String phone;
    private String userId;
    private String dateAdded;
    private String partitionKey; 
    private String sortKey;

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }
    public UserBuilder name(String name) {
      this.name = name;
      return this;
    }
    public UserBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }
    public UserBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }
    public UserBuilder dateAdded(String dateAdded) {
      this.dateAdded = dateAdded;
      return this;
    }
    public UserBuilder partitionKey(String partitionKey) {
      this.partitionKey = partitionKey;
      return this;
    }
    public UserBuilder sortKey(String sortKey) {
      this.sortKey = sortKey;
      return this;
    } 
    public User build() {
      return new User(this);
    }
  }

  public static class UserKeyBuilder {
    public static String makePartitionKey(String id) {
        return USER_PK_PREFIX + id;
    }

    public static String makeSortKey(String id) {
        return USER_SK_PREFIX + id;
    }
  }
}



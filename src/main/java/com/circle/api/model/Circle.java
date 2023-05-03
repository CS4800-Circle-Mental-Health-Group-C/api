package com.circle.api.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Circle extends DynamoDbBase{
  public static final String CIRCLE_PK_PREFIX = "CIRCLE#";
  public static final String CIRCLE_SK_PREFIX = "CIRCLE#";

  private String email;
  private String name;
  private String phone;
  private String userId;
  private String circleMemberId;
  private String dateAdded;

    public Circle() {}

    public Circle(CircleBuilder circleBuilder) {
        this.email = circleBuilder.email; 
        this.name = circleBuilder.name;
        this.phone = circleBuilder.phone;
        this.userId = circleBuilder.userId;
        this.circleMemberId = circleBuilder.circleMemberId;
        this.dateAdded = circleBuilder.dateAdded;
        this.partitionKey = circleBuilder.partitionKey;
        this.sortKey = circleBuilder.sortKey;
    }

    @DynamoDbAttribute("Email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDbAttribute("Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("Phone")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDbAttribute("User Id")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDbAttribute("Circle Member Id")
    public String getCircleMemberId() {
        return this.circleMemberId;
    }

    public void setCircleMemberId(String circleMemberId) {
        this.circleMemberId = circleMemberId;
    }

    @DynamoDbAttribute("Date Added")
    public String getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public static CircleBuilder builder() {
        return new CircleBuilder();
    }

    public static class CircleBuilder { 
        private String email;
        private String name;
        private String phone;
        private String userId;
        private String circleMemberId;
        private String dateAdded;
        private String partitionKey; 
        private String sortKey;

        public CircleBuilder email(String email) {
            this.email = email;
            return this;
        }
        public CircleBuilder name(String name) {
            this.name = name;
            return this;
        }
        public CircleBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public CircleBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }
        public CircleBuilder circleMemberId(String circleMemberId) {
            this.circleMemberId = circleMemberId;
            return this;
        }
        public CircleBuilder dateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }
        public CircleBuilder partitionKey(String partitionKey) {
            this.partitionKey = partitionKey;
            return this;
        }
      
        public CircleBuilder sortKey(String sortKey) {
            this.sortKey = sortKey;
            return this;
        }

        public Circle build() {
          return new Circle(this);
        }

    }

    public static class CircleKeyBuilder {
        public static String makePartitionKey(String id) {
            return CIRCLE_PK_PREFIX + id;
        }
    
        public static String makeSortKey(String id) {
            return CIRCLE_SK_PREFIX + id;
        }
    }
  
}

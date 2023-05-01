package com.circle.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.circle.api.model.Circle;
import com.circle.api.model.Survey;
import com.circle.api.model.User;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration(proxyBeanMethods = false)
public class DynamoDBConfig {

  @Bean
  public DynamoDbClient dynamoDbClient() {
    return DynamoDbClient.builder().region(Region.US_WEST_1).build();  
  }

  @Bean
  public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
    return DynamoDbEnhancedClient.builder()
                                 .dynamoDbClient(dynamoDbClient)
                                 .build();
  }

  @Bean
    public DynamoDbTable<User> userTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient(dynamoDbClient()).table("User", TableSchema.fromBean(User.class));
    }

  @Bean
  public DynamoDbTable<Survey> surveyTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient(dynamoDbClient()).table("User", TableSchema.fromBean(Survey.class));
    }

  @Bean
  public DynamoDbTable<Circle> circleTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient(dynamoDbClient()).table("User", TableSchema.fromBean(Circle.class));
    }
}

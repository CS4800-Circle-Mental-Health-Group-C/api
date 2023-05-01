package com.circle.api.model;

import java.util.Map;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;

@DynamoDbBean
public class Survey extends DynamoDbBase {
  public static final String SURVEY_PK_PREFIX = "USER#";
  public static final String SURVEY_SK_PREFIX = "SURVEY#";

  private String userId; 
  private String surveyFormId;
  private String dateTaken;
  private Map<String,String> response;

  public Survey() {};

  public Survey(SurveyBuilder surveyBuilder) {
    this.userId = surveyBuilder.userId; 
    this.surveyFormId = surveyBuilder.surveyFormId;
    this.dateTaken = surveyBuilder.dateTaken;
    this.response = surveyBuilder.response;
  }

  public void setKey(String id) {
    setPartitionKey(SurveyKeyBuilder.makePartitionKey(id));
  }
  
  @DynamoDbAttribute("User Id")
  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @DynamoDbAttribute("Survey Form Id")
  public String getSurveyFormId() {
    return this.surveyFormId;
  }

  public void setSurveyFormId(String surveyFormId) {
    this.surveyFormId = surveyFormId;
  }

  @DynamoDbAttribute("Date Taken")
  public String getDateTaken() {
    return this.dateTaken;
  }

  public void setDateTaken(String dateTaken) {
    this.dateTaken = dateTaken;
  }

  @DynamoDbAttribute("Response")
  public Map<String,String> getResponse() {
    return this.response;
  }

  public void setResponse(Map<String,String> response) {
    this.response = response;
  }

  public static SurveyBuilder builder() {
    return new SurveyBuilder();
  }

  public static class SurveyBuilder {
    private String userId; 
    private String surveyFormId;
    private String dateTaken;
    private Map<String,String> response;
    private String partitionKey;
    private String sortKey;

    public SurveyBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }
    public SurveyBuilder surveyFormId(String surveyFormId) {
        this.surveyFormId = surveyFormId;
        return this;
    }
    public SurveyBuilder dateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
        return this;
    }
    public SurveyBuilder response(Map<String,String> response) {
        this.response = response; 
        return this;
    }
    public SurveyBuilder partitionKey(String partitionKey) {
        this.partitionKey = SurveyKeyBuilder.makePartitionKey(partitionKey);
        return this;
    }
    public SurveyBuilder sortKey(String sortKey) {
        this.sortKey = SurveyKeyBuilder.makeSortKey(sortKey);
        return this;
    }
    public Survey build() {
        return new Survey(this);
    }
  }

  public static class SurveyKeyBuilder {
    public static String makePartitionKey(String id) {
        return SURVEY_PK_PREFIX + id;
    }

    public static String makeSortKey(String id) {
        return SURVEY_SK_PREFIX + id;
    }
  }
}

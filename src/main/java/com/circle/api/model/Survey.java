package com.circle.api.model;

import java.util.List;
import java.util.Map;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Survey extends DynamoDbBase {
  public static final String SURVEY_PK_PREFIX = "SURVEY#";
  public static final String SURVEY_SK_PREFIX = "SURVEY#";

  private String userId; 
  private String surveyFormId;
  private String dateTaken;
  private List<String> surveyQuestions;
  private List<String> surveyAnswers; 
  private Map<String,String> surveyResponses;

  public Survey() {};

  public Survey(SurveyBuilder surveyBuilder) {
    this.userId = surveyBuilder.userId; 
    this.surveyFormId = surveyBuilder.surveyFormId;
    this.dateTaken = surveyBuilder.dateTaken;
    this.surveyQuestions = surveyBuilder.surveyQuestions;
    this.surveyAnswers = surveyBuilder.surveyAnswers;
    this.surveyResponses = surveyBuilder.surveyResponses;
  }

  public void setKey(String id) {
    setPartitionKey(SurveyKeyBuilder.makePartitionKey(id));
    setSortKey(SurveyKeyBuilder.makeSortKey(id));
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

  @DynamoDbAttribute("Survey Questions")
  public List<String> getSurveyQuestions() {
    return this.surveyQuestions;
  }

  public void setSurveyQuestions(List<String> surveyQuestions) {
    this.surveyQuestions = surveyQuestions;
  }

  @DynamoDbAttribute("Survey Answers")
  public List<String> getSurveyAnswers() {
    return this.surveyAnswers;
  }

  public void setSurveyAnswers(List<String> surveyAnswers) {
    this.surveyAnswers = surveyAnswers;
  }

  @DynamoDbAttribute("Survey Responses")
  public Map<String,String> getSurveyResponses() {
    return this.surveyResponses;
  }

  public void setSurveyResponses(Map<String,String> surveyResponses) {
    this.surveyResponses = surveyResponses;
  }

  public static SurveyBuilder builder() {
    return new SurveyBuilder();
  }

  public static class SurveyBuilder {
    private String userId; 
    private String surveyFormId;
    private String dateTaken;
    private List<String> surveyQuestions;
    private List<String> surveyAnswers; 
    private Map<String,String> surveyResponses;
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
    public SurveyBuilder surveyQuestions(List<String> surveyQuestions) {
        this.surveyQuestions = surveyQuestions; 
        return this;
    }
    public SurveyBuilder surveyAnswers(List<String> surveyAnswers) {
        this.surveyAnswers = surveyAnswers; 
        return this;
    }
    public SurveyBuilder surveyResponses(Map<String,String> surveyResponses) {
        this.surveyResponses = surveyResponses; 
        return this;
    }
    public SurveyBuilder partitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
        return this;
    }
    public SurveyBuilder sortKey(String sortKey) {
        this.sortKey = sortKey;
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

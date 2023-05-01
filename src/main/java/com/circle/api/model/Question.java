package com.circle.api.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Question extends DynamoDbBase {
  public static final String QUESTION_PK_PREFIX = "SURVEY#";
  public static final String QUESTION_SK_PREFIX = "QUESTION#";
  
  private String userId; 
  private String surveyFormId;
  private String dateTaken;
  private String questionId; 
  private String question;
  private String response; 

  public Question() {}; 

  public Question(QuestionBuilder questionBuilder) {
    this.userId = questionBuilder.userId; 
    this.surveyFormId = questionBuilder.surveyFormId;
    this.dateTaken = questionBuilder.dateTaken;
    this.questionId = questionBuilder.questionId;
    this.question = questionBuilder.question;
    this.response = questionBuilder.response;
    this.partitionKey = questionBuilder.partitionKey;
    this.sortKey = questionBuilder.sortKey;
  }

  // Setters and Getters
  @DynamoDbAttribute("User Id")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @DynamoDbAttribute("Survey Form Id")
  public String getSurveyFormId() {
    return surveyFormId;
  }

  public void setSurveyFormId(String surveyFormId) {
    this.surveyFormId = surveyFormId;
  }

  @DynamoDbAttribute("Date Taken")
  public String getDateTaken() {
    return dateTaken;
  }

  public void setDateTaken(String dateTaken) {
    this.dateTaken = dateTaken;
  }

  @DynamoDbAttribute("Question Id")
  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  @DynamoDbAttribute("Question")
  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  @DynamoDbAttribute("Response")
  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public static QuestionBuilder builder() {
    return new QuestionBuilder();
  }

  public static class QuestionBuilder {

    private String userId; 
    private String surveyFormId;
    private String dateTaken;
    private String questionId; 
    private String question;
    private String response; 
    private String partitionKey;
    private String sortKey;

    public QuestionBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }
    public QuestionBuilder surveyFormId(String surveyFormId) {
        this.surveyFormId = surveyFormId;
        return this;
    }
    public QuestionBuilder dateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
        return this;
    }
    public QuestionBuilder questionId(String questionId) {
        this.questionId = questionId;
        return this;
    }
    public QuestionBuilder question(String question) {
        this.question = question;
        return this;
    }
    public QuestionBuilder response(String response) {
        this.response = response;
        return this;
    }
    public QuestionBuilder partitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
        return this;
    }
    public QuestionBuilder sortKey(String sortKey) {
        this.sortKey = sortKey;
        return this;
    }
    public Question build() {
        return new Question(this);
    }
  }

  public static class QuestionKeyBuilder {
    public static String makePartitionKey(String id) {
        return QUESTION_PK_PREFIX + id;
    }

    public static String makeSortKey(String id) {
        return QUESTION_SK_PREFIX + id;
    }
  }

}

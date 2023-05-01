package com.circle.api.service;

import org.springframework.stereotype.Service;
import com.circle.api.model.Question;
import com.circle.api.repository.QuestionRepository;

@Service
public class QuestionService {
    
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question findByIds(String surveyId, String questionId) {
        return questionRepository.findBySurveyIdQuestionId(surveyId, questionId);
    }
}

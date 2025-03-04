package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Question;
import com.fabio.sarcinelli.eduhub_backend.repository.QuestionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionService {
    
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question save(Question question) {
        try {
            log.info("Saving question");
            return questionRepository.save(question);
        } catch (Exception e) {
            log.error("Saving question failed: {} - question: {}", e, question);
            return null;
        }
    }

    public Question update(Question updatedQuestion) {
        log.info("Updating question with ID: {}", updatedQuestion.getId());

        Optional<Question> optionalQuestion = questionRepository.findById(updatedQuestion.getId());
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();

            existingQuestion.setAnswers(updatedQuestion.getAnswers());
            existingQuestion.setText(updatedQuestion.getText());
            existingQuestion.setQuiz(updatedQuestion.getQuiz());

            return questionRepository.save(existingQuestion);
        } else {
            log.error("Question with ID {} not found.", updatedQuestion.getId());
            throw new IllegalArgumentException("Question with ID " + updatedQuestion.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting question with ID: {}", id);
            questionRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting question failed: {} - question ID: {}", e, id);
        }
    }

    public void delete(Question question) {
        try {
            log.info("Deleting question: {}", question);
            questionRepository.delete(question);
        } catch (Exception e) {
            log.error("Deleting question failed: {} - question: {}", e, question);
        }
    }

    public Optional<Question> findById(Long id) {
        log.info("Finding question by ID: {}", id);
        try{
            return questionRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding question failed: {} - question ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Question> findAll() {
        log.info("Finding all questions");
        try {
            return questionRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all questions failed: {}", e);
            return List.of();
        }
    }   
}

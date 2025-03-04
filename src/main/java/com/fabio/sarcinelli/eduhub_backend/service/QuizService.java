package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Quiz;
import com.fabio.sarcinelli.eduhub_backend.repository.QuizRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizService {
    
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void save(Quiz quiz) {
        try {
            log.info("Saving quiz");
            quizRepository.save(quiz);
        } catch (Exception e) {
            log.error("Saving quiz failed: {} - quiz: {}", e, quiz);
        }
    }

    public void update(Quiz updatedQuiz) {
        log.info("Updating quiz with ID: {}", updatedQuiz.getId());

        Optional<Quiz> optionalQuiz = quizRepository.findById(updatedQuiz.getId());
        if (optionalQuiz.isPresent()) {
            Quiz existingQuiz = optionalQuiz.get();

            existingQuiz.setTitle(updatedQuiz.getTitle());
            existingQuiz.setPublishedAt(updatedQuiz.getPublishedAt());
            existingQuiz.setQuestions(updatedQuiz.getQuestions());
            existingQuiz.setCourse(updatedQuiz.getCourse());

            quizRepository.save(existingQuiz);
        } else {
            log.error("Quiz with ID {} not found.", updatedQuiz.getId());
            throw new IllegalArgumentException("Quiz with ID " + updatedQuiz.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting quiz with ID: {}", id);
            quizRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting quiz failed: {} - quiz ID: {}", e, id);
        }
    }

    public void delete(Quiz quiz) {
        try {
            log.info("Deleting quiz: {}", quiz);
            quizRepository.delete(quiz);
        } catch (Exception e) {
            log.error("Deleting quiz failed: {} - quiz: {}", e, quiz);
        }
    }

    public Optional<Quiz> findById(Long id) {
        log.info("Finding quiz by ID: {}", id);
        try{
            return quizRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding quiz failed: {} - quiz ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Quiz> findAll() {
        log.info("Finding all quizzes");
        try {
            return quizRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all quizzes failed: {}", e);
            return List.of();
        }
    }   
}

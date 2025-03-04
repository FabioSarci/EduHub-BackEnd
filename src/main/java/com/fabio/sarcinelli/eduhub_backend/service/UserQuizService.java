package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.UserQuiz;
import com.fabio.sarcinelli.eduhub_backend.repository.UserQuizRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserQuizService {
    
    private final UserQuizRepository userQuizRepository;

    public UserQuizService(UserQuizRepository userQuizRepository) {
        this.userQuizRepository = userQuizRepository;
    }

    public void save(UserQuiz userQuiz) {
        try {
            log.info("Saving userQuiz");
            userQuizRepository.save(userQuiz);
        } catch (Exception e) {
            log.error("Saving userQuiz failed: {} - userQuiz: {}", e, userQuiz);
        }
    }

    public void update(UserQuiz updatedUserQuiz) {
        log.info("Updating userQuiz with ID: {}", updatedUserQuiz.getId());

        Optional<UserQuiz> optionalUserQuiz = userQuizRepository.findById(updatedUserQuiz.getId());
        if (optionalUserQuiz.isPresent()) {
            UserQuiz existingUserQuiz = optionalUserQuiz.get();

            existingUserQuiz.setAnswers(updatedUserQuiz.getAnswers());
            existingUserQuiz.setCompletedAt(updatedUserQuiz.getCompletedAt());
            existingUserQuiz.setUser(updatedUserQuiz.getUser());
            existingUserQuiz.setQuiz(updatedUserQuiz.getQuiz());

            userQuizRepository.save(existingUserQuiz);
        } else {
            log.error("UserQuiz with ID {} not found.", updatedUserQuiz.getId());
            throw new IllegalArgumentException("UserQuiz with ID " + updatedUserQuiz.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting userQuiz with ID: {}", id);
            userQuizRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting userQuiz failed: {} - userQuiz ID: {}", e, id);
        }
    }

    public void delete(UserQuiz userQuiz) {
        try {
            log.info("Deleting userQuiz: {}", userQuiz);
            userQuizRepository.delete(userQuiz);
        } catch (Exception e) {
            log.error("Deleting userQuiz failed: {} - userQuiz: {}", e, userQuiz);
        }
    }

    public Optional<UserQuiz> findById(Long id) {
        log.info("Finding userQuiz by ID: {}", id);
        try{
            return userQuizRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding userQuiz failed: {} - userQuiz ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<UserQuiz> findAll() {
        log.info("Finding all userQuizzes");
        try {
            return userQuizRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all userQuizzes failed: {}", e);
            return List.of();
        }
    }   
}

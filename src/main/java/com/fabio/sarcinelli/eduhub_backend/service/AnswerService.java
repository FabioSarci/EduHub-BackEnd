package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Answer;
import com.fabio.sarcinelli.eduhub_backend.repository.AnswerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnswerService {
    
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void save(Answer answer) {
        try {
            log.info("Saving answer");
            answerRepository.save(answer);
        } catch (Exception e) {
            log.error("Saving answer failed: {} - answer: {}", e, answer);
        }
    }

    public void update(Answer updatedAnswer) {
        log.info("Updating answer with ID: {}", updatedAnswer.getId());

        Optional<Answer> optionalAnswer = answerRepository.findById(updatedAnswer.getId());
        if (optionalAnswer.isPresent()) {
            Answer existingAnswer = optionalAnswer.get();

            existingAnswer.setText(updatedAnswer.getText());
            existingAnswer.setCorrect(updatedAnswer.isCorrect());
            existingAnswer.setQuestion(updatedAnswer.getQuestion());

            answerRepository.save(existingAnswer);
        } else {
            log.error("Answer with ID {} not found.", updatedAnswer.getId());
            throw new IllegalArgumentException("Answer with ID " + updatedAnswer.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting answer with ID: {}", id);
            answerRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting answer failed: {} - answer ID: {}", e, id);
        }
    }

    public void delete(Answer answer) {
        try {
            log.info("Deleting answer: {}", answer);
            answerRepository.delete(answer);
        } catch (Exception e) {
            log.error("Deleting answer failed: {} - answer: {}", e, answer);
        }
    }

    public Optional<Answer> findById(Long id) {
        log.info("Finding answer by ID: {}", id);
        try{
            return answerRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding answer failed: {} - answer ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Answer> findAll() {
        log.info("Finding all answers");
        try {
            return answerRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all answers failed: {}", e);
            return List.of();
        }
    }   
}

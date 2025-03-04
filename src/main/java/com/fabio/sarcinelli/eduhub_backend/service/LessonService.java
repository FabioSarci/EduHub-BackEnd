package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Lesson;
import com.fabio.sarcinelli.eduhub_backend.repository.LessonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LessonService {
    
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void save(Lesson lesson) {
        try {
            log.info("Saving lesson");
            lessonRepository.save(lesson);
        } catch (Exception e) {
            log.error("Saving lesson failed: {} - lesson: {}", e, lesson);
        }
    }

    public void update(Lesson updatedLesson) {
        log.info("Updating lesson with ID: {}", updatedLesson.getId());

        Optional<Lesson> optionalLesson = lessonRepository.findById(updatedLesson.getId());
        if (optionalLesson.isPresent()) {
            Lesson existingLesson = optionalLesson.get();

            existingLesson.setDate(updatedLesson.getDate());
            existingLesson.setDescription(updatedLesson.getDescription());
            existingLesson.setTopic(updatedLesson.getTopic());

            lessonRepository.save(existingLesson);
        } else {
            log.error("Lesson with ID {} not found.", updatedLesson.getId());
            throw new IllegalArgumentException("Lesson with ID " + updatedLesson.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting lesson with ID: {}", id);
            lessonRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting lesson failed: {} - lesson ID: {}", e, id);
        }
    }

    public void delete(Lesson lesson) {
        try {
            log.info("Deleting lesson: {}", lesson);
            lessonRepository.delete(lesson);
        } catch (Exception e) {
            log.error("Deleting lesson failed: {} - lesson: {}", e, lesson);
        }
    }

    public Optional<Lesson> findById(Long id) {
        log.info("Finding lesson by ID: {}", id);
        try{
            return lessonRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding lesson failed: {} - lesson ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Lesson> findAll() {
        log.info("Finding all lessons");
        try {
            return lessonRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all lessons failed: {}", e);
            return List.of();
        }
    }   
}

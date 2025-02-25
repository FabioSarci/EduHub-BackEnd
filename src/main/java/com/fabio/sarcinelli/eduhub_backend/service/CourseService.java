package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Course;
import com.fabio.sarcinelli.eduhub_backend.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course save(Course course){
        try {
            log.info("Saving course");
            return courseRepository.save(course);
        } catch (Exception e) {
            log.error("Saving course failed: {} - course: {}", e, course);
            return null;
        }
    }

    public Course update(Course updatedCourse){

        log.info("Updating course with ID: {}", updatedCourse.getId());

        Optional<Course> optionalCourse = courseRepository.findById(updatedCourse.getId());
        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();

            existingCourse.setName(updatedCourse.getName());
            existingCourse.setSection(updatedCourse.getSection());
            existingCourse.setSubject(updatedCourse.getSubject());

            return courseRepository.save(existingCourse);
        } else {
            log.error("Course with ID {} not found.", updatedCourse.getId());
            throw new IllegalArgumentException("Course with ID " + updatedCourse.getId() + " not found.");
        }
    }

    public void delete(Course course) {
        try {
            log.info("Deleting course: {}", course);
            courseRepository.delete(course);
        } catch (Exception e) {
            log.error("Deleting course failed: {} - course: {}", e, course);
        }
    }

    public void deleteById(Long id) {
        try {
            log.info("Deleting course with ID: {}", id);
            courseRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting course failed: {} - course ID: {}", e, id);
        }
    }

    public Optional<Course> findById(Long id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding course failed: {} - course ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Course> findAll() {
        log.info("Finding all courses");
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            log.error("Finding courses failed: {}", e);
            return List.of();
        }
    }
    
}

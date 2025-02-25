package com.fabio.sarcinelli.eduhub_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.sarcinelli.eduhub_backend.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}

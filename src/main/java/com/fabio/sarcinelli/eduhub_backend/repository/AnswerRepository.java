package com.fabio.sarcinelli.eduhub_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.sarcinelli.eduhub_backend.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}

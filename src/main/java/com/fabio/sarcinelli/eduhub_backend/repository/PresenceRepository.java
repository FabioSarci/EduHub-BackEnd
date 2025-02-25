package com.fabio.sarcinelli.eduhub_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.sarcinelli.eduhub_backend.model.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
    
}

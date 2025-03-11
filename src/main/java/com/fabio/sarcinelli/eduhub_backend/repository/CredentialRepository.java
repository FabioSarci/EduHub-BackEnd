package com.fabio.sarcinelli.eduhub_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    public Credential findByEmail(String email);

    Optional<Credential> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
}

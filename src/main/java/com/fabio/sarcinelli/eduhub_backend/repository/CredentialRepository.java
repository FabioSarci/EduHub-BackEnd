package com.fabio.sarcinelli.eduhub_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    public Credential findByEmail(String email);
    
}

package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import lombok.SneakyThrows;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.repository.CredentialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialService {
    
    private final CredentialRepository credentialRepository;  
    
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public Credential save(Credential credential) {
        try {
            log.info("Saving credential");
            return credentialRepository.save(credential);
        } catch (Exception e) {
            log.error("Saving credential failed: {} - credential: {}", e, credential);
            return null;
        }
    }

    public Credential update(Credential updatedCredential) {
        log.info("Updating credential with ID: {}", updatedCredential.getId());

        Optional<Credential> optionalCredential = credentialRepository.findById(updatedCredential.getId());
        if (optionalCredential.isPresent()) {
            Credential existingCredential = optionalCredential.get();

        
            existingCredential.setEmail(updatedCredential.getEmail());
            existingCredential.setPassword(updatedCredential.getPassword());
            existingCredential.setUsers(updatedCredential.getUsers());

            return credentialRepository.save(existingCredential);
        } else {
            log.error("Credential with ID {} not found.", updatedCredential.getId());
            throw new IllegalArgumentException("Credential with ID " + updatedCredential.getId() + " not found.");
        }
    }

    public void delete(Credential credential) {
        try {
            log.info("Deleting credential: {}", credential);
            credentialRepository.delete(credential);
        } catch (Exception e) {
            log.error("Deleting credential failed: {} - credential: {}", e, credential);
        }
    }

    public void deleteById(Long id) {
        try {
            log.info("Deleting credential with ID: {}", id);
            credentialRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting credential failed: {} - credential ID: {}", e, id);
        }
    }

    public Optional<Credential> findById(Long id) {
        log.info("Finding credential by ID: {}", id);
        return credentialRepository.findById(id);
    }

    public List<Credential> findAll() {
        try {
            log.info("Finding all credentials");
            return credentialRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all credentials failed: {}", e);
            return List.of();
        }
    }

    @SneakyThrows
    public Credential findByEmail(String email){

        return credentialRepository.findByEmail(email);
    }
}
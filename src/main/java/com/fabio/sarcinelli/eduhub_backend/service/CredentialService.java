package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.SneakyThrows;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.dto.request.CredentialAndUserDtoForm;
import com.fabio.sarcinelli.eduhub_backend.dto.response.CredentialDto;
import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.model.Users;
import com.fabio.sarcinelli.eduhub_backend.repository.CredentialRepository;
import com.fabio.sarcinelli.eduhub_backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialService {
    
    private final CredentialRepository credentialRepository;  
    private final UserRepository userRepository;
    
    public CredentialService(CredentialRepository credentialRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;

    }

    public CredentialAndUserDtoForm save(CredentialAndUserDtoForm credential) {
        try {
            log.info("Saving credential");
            Credential newCredential = new Credential();
            newCredential.setEmail(credential.getEmail());
            newCredential.setPassword(credential.getPassword());
            newCredential.setUsername(credential.getUsername());
            newCredential.setRoles(credential.getRoles());

            Users user = new Users();
            user.setName(credential.getName());
            user.setSurname(credential.getSurname());
            user.setBirthDate(credential.getBirthDate());
            user.setCredential(newCredential);

            newCredential.setUser(user);

            userRepository.save(user);
            credentialRepository.save(newCredential);

            return credential;

        } catch (Exception e) {
            log.error("Saving credential failed: {} - credential: {}", e, credential);
            return null;
        }
    }

    public CredentialDto update(Credential updatedCredential) {
        log.info("Updating credential with ID: {}", updatedCredential.getId());

        Optional<Credential> optionalCredential = credentialRepository.findById(updatedCredential.getId());
        if (optionalCredential.isPresent()) {
            Credential existingCredential = optionalCredential.get();

        
            existingCredential.setEmail(updatedCredential.getEmail());
            existingCredential.setPassword(updatedCredential.getPassword());
            existingCredential.setUser(updatedCredential.getUser());

            credentialRepository.save(existingCredential);

            return new CredentialDto(existingCredential.getUsername(), existingCredential.getEmail(), existingCredential.getRoles());
        } else {
            log.error("Credential with ID {} not found.", updatedCredential.getId());
            throw new IllegalArgumentException("Credential with ID " + updatedCredential.getId() + " not found.");
        }
    }

    public void delete(Credential credential) {
        try {
            log.info("Deleting credential: {} - {}", credential, credential.getUser());
            userRepository.delete(credential.getUser());
            credentialRepository.delete(credential);
        } catch (Exception e) {
            log.error("Deleting credential failed: {} - credential: {}", e, credential);
        }
    }

    public void deleteById(Long id) {
        try {
            log.info("Deleting credential with ID: {}", id);

            Credential credential = credentialRepository.findById(id).orElseThrow();
            userRepository.deleteById(credential.getUser().getId());
            
            credentialRepository.deleteById(id);
            
        } catch (Exception e) {
            log.error("Deleting credential failed: {} - credential ID: {}", e, id);
        }
    }

    public CredentialDto findById(Long id) {
        try {
            log.info("Finding credential by ID: {}", id);
            Optional<Credential> credential = credentialRepository.findById(id);
            if (credential.isPresent()) {
                return new CredentialDto(credential.get().getUsername(), credential.get().getEmail(), credential.get().getRoles());
            } else {
                throw new NoSuchElementException("Credential with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<CredentialDto> findAll() {
        try {
            log.info("Finding all credentials");
            List<Credential> credential = credentialRepository.findAll();
            return credential.stream()
                .map(c -> new CredentialDto(c.getUsername(), c.getEmail(), c.getRoles()))
                .toList();
                
        } catch (Exception e) {
            log.error("Finding all credentials failed: {}", e);
            return List.of();
        }
    }

    @SneakyThrows
    public CredentialDto findByEmail(String email){

        try {
            log.info("Finding credential by email: {}", email);
            Credential credential = credentialRepository.findByEmail(email);
            return new CredentialDto(credential.getUsername(), credential.getEmail(), credential.getRoles());
        } catch (Exception e) {
            throw new NoSuchElementException("Credential with email " + email + " not found.");
        }
    }

    public Optional<Credential> findByUsername(String username) {
        return credentialRepository.findByUsername(username);
    }

    public Boolean existsByUsername(String username) {
        return credentialRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return credentialRepository.existsByEmail(email);
    }
}
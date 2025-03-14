package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.model.Users;
import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.repository.CredentialRepository;
import com.fabio.sarcinelli.eduhub_backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    public UserService(UserRepository userRepository, CredentialRepository credentialRepository) {
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
    }


    public Users update(Users updatedUsers) {
        log.info("Updating user with ID: {}", updatedUsers.getId());

        Optional<Users> optionalUser = userRepository.findById(updatedUsers.getId());
        if (optionalUser.isPresent()) {
            Users existingUsers = optionalUser.get();

            existingUsers.setName(updatedUsers.getName());
            existingUsers.setSurname(updatedUsers.getSurname());
            existingUsers.setBirthDate(updatedUsers.getBirthDate());
            existingUsers.setCredential(updatedUsers.getCredential());
            existingUsers.setCourses(updatedUsers.getCourses());

            return userRepository.save(existingUsers);
        } else {
            log.error("User with ID {} not found.", updatedUsers.getId());
            throw new IllegalArgumentException("User with ID " + updatedUsers.getId() + " not found.");
        }
    }

    public Optional<Users> findById(Long id) {
        log.info("Finding user by ID: {}", id);
        try{
            return userRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding user failed: {} - user ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Users> findAll() {
        log.info("Finding all users");
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all users failed: {}", e);
            return List.of();
        }
    }   

    public Users findUserByEmail(String email){
        log.info("Finding user by credential: {}", email);
        Credential credential = credentialRepository.findByEmail(email);
        try {
            if (credential == null) {
                return null;
            }
            return userRepository.findByCredential(credential);
        } catch (Exception e) {
            log.error("Finding user by credential failed: {} - credential: {}", e, credential);
            return null;
        }
    }
}

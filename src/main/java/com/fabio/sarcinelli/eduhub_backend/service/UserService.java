package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.User;
import com.fabio.sarcinelli.eduhub_backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        try {
            log.info("Saving user");
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Saving user failed: {} - user: {}", e, user);
        }
    }

    public void update(User updatedUser) {
        log.info("Updating user with ID: {}", updatedUser.getId());

        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setBirthDate(updatedUser.getBirthDate());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setCredential(updatedUser.getCredential());
            existingUser.setCourses(updatedUser.getCourses());

            userRepository.save(existingUser);
        } else {
            log.error("User with ID {} not found.", updatedUser.getId());
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting user with ID: {}", id);
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting user failed: {} - user ID: {}", e, id);
        }
    }

    public void delete(User user) {
        try {
            log.info("Deleting user: {}", user);
            userRepository.delete(user);
        } catch (Exception e) {
            log.error("Deleting user failed: {} - user: {}", e, user);
        }
    }

    public Optional<User> findById(Long id) {
        log.info("Finding user by ID: {}", id);
        try{
            return userRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding user failed: {} - user ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<User> findAll() {
        log.info("Finding all users");
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all users failed: {}", e);
            return List.of();
        }
    }   
}

package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import com.fabio.sarcinelli.eduhub_backend.model.Users;
import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users save(Users users) {
        try {
            log.info("Saving user");
            return userRepository.save(users);
        } catch (Exception e) {
            log.error("Saving user failed: {} - user: {}", e, users);
            return null;
        }
    }

    public Users update(Users updatedUsers) {
        log.info("Updating user with ID: {}", updatedUsers.getId());

        Optional<Users> optionalUser = userRepository.findById(updatedUsers.getId());
        if (optionalUser.isPresent()) {
            Users existingUsers = optionalUser.get();

            existingUsers.setName(updatedUsers.getName());
            existingUsers.setSurname(updatedUsers.getSurname());
            existingUsers.setBirthDate(updatedUsers.getBirthDate());
            existingUsers.setRole(updatedUsers.getRole());
            existingUsers.setCredential(updatedUsers.getCredential());
            existingUsers.setCourses(updatedUsers.getCourses());

            return userRepository.save(existingUsers);
        } else {
            log.error("User with ID {} not found.", updatedUsers.getId());
            throw new IllegalArgumentException("User with ID " + updatedUsers.getId() + " not found.");
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

    public void delete(Users users) {
        try {
            log.info("Deleting user: {}", users);
            userRepository.delete(users);
        } catch (Exception e) {
            log.error("Deleting user failed: {} - user: {}", e, users);
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
}

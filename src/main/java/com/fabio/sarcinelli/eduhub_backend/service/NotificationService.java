package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Notification;
import com.fabio.sarcinelli.eduhub_backend.repository.NotificationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {
    
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void save(Notification notification) {
        try {
            log.info("Saving notification");
            notificationRepository.save(notification);
        } catch (Exception e) {
            log.error("Saving notification failed: {} - notification: {}", e, notification);
        }
    }

    public void update(Notification updatedNotification) {
        log.info("Updating notification with ID: {}", updatedNotification.getId());

        Optional<Notification> optionalNotification = notificationRepository.findById(updatedNotification.getId());
        if (optionalNotification.isPresent()) {
            Notification existingNotification = optionalNotification.get();

            existingNotification.setBody(updatedNotification.getBody());
            existingNotification.setTitle(updatedNotification.getTitle());
            existingNotification.setUser(updatedNotification.getUser());

            notificationRepository.save(existingNotification);
        } else {
            log.error("Notification with ID {} not found.", updatedNotification.getId());
            throw new IllegalArgumentException("Notification with ID " + updatedNotification.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting notification with ID: {}", id);
            notificationRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting notification failed: {} - notification ID: {}", e, id);
        }
    }

    public void delete(Notification notification) {
        try {
            log.info("Deleting notification: {}", notification);
            notificationRepository.delete(notification);
        } catch (Exception e) {
            log.error("Deleting notification failed: {} - notification: {}", e, notification);
        }
    }

    public Optional<Notification> findById(Long id) {
        log.info("Finding notification by ID: {}", id);
        try{
            return notificationRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding notification failed: {} - notification ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Notification> findAll() {
        log.info("Finding all notifications");
        try {
            return notificationRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all notifications failed: {}", e);
            return List.of();
        }
    }   
}

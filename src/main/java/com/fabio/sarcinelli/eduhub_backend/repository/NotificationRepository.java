package com.fabio.sarcinelli.eduhub_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.sarcinelli.eduhub_backend.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
}

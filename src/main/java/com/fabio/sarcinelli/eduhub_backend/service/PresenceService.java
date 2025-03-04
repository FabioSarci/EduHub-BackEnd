package com.fabio.sarcinelli.eduhub_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Presence;
import com.fabio.sarcinelli.eduhub_backend.repository.PresenceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PresenceService {
    
    private final PresenceRepository presenceRepository;

    public PresenceService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    public Presence save(Presence presence) {
        try {
            log.info("Saving presence");
            return presenceRepository.save(presence);
        } catch (Exception e) {
            log.error("Saving presence failed: {} - presence: {}", e, presence);
            return null;
        }
    }

    public Presence update(Presence updatedPresence) {
        log.info("Updating presence with ID: {}", updatedPresence.getId());

        Optional<Presence> optionalPresence = presenceRepository.findById(updatedPresence.getId());
        if (optionalPresence.isPresent()) {
            Presence existingPresence = optionalPresence.get();

            existingPresence.setPresent(updatedPresence.isPresent());
            existingPresence.setUser(updatedPresence.getUser());
            existingPresence.setLesson(updatedPresence.getLesson());

            return presenceRepository.save(existingPresence);
        } else {
            log.error("Presence with ID {} not found.", updatedPresence.getId());
            throw new IllegalArgumentException("Presence with ID " + updatedPresence.getId() + " not found.");
        }
    }
    
    public void deleteById(Long id) {
        try {
            log.info("Deleting presence with ID: {}", id);
            presenceRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting presence failed: {} - presence ID: {}", e, id);
        }
    }

    public void delete(Presence presence) {
        try {
            log.info("Deleting presence: {}", presence);
            presenceRepository.delete(presence);
        } catch (Exception e) {
            log.error("Deleting presence failed: {} - presence: {}", e, presence);
        }
    }

    public Optional<Presence> findById(Long id) {
        log.info("Finding presence by ID: {}", id);
        try{
            return presenceRepository.findById(id);
        } catch (Exception e) {
            log.error("Finding presence failed: {} - presence ID: {}", e, id);
            return Optional.empty();
        }
    }

    public List<Presence> findAll() {
        log.info("Finding all presences");
        try {
            return presenceRepository.findAll();
        } catch (Exception e) {
            log.error("Finding all presences failed: {}", e);
            return List.of();
        }
    }   
}

package com.fabio.sarcinelli.eduhub_backend.service;

import org.springframework.stereotype.Service;

import com.fabio.sarcinelli.eduhub_backend.model.Role;
import com.fabio.sarcinelli.eduhub_backend.repository.RoleRepository;
import com.fabio.sarcinelli.eduhub_backend.util.ERole;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        try {
            log.info("Saving role");
            return roleRepository.save(role);
        } catch (Exception e) {
            log.error("Saving role failed: {} - role: {}", e, role);
            return null;
        }
    }

    public Role update(Role updatedRole) {
        log.info("Updating role with ID: {}", updatedRole.getId());

        Role existingRole = roleRepository.findById(updatedRole.getId()).orElse(null);
        if (existingRole != null) {
            existingRole.setName(updatedRole.getName());
            return roleRepository.save(existingRole);
        } else {
            log.error("Role with ID {} not found.", updatedRole.getId());
            throw new IllegalArgumentException("Role with ID " + updatedRole.getId() + " not found.");
        }
    }

    public void deleteById(Long id) {
        try {
            log.info("Deleting role with ID: {}", id);
            roleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Deleting role failed: {} - ID: {}", e, id);
        }
    }

    public Role findByName(ERole name) {
        return roleRepository.findByName(name).orElse(null);
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }


    
}

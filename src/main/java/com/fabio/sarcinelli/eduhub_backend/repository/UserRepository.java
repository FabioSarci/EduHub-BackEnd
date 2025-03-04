package com.fabio.sarcinelli.eduhub_backend.repository;

import com.fabio.sarcinelli.eduhub_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    
}

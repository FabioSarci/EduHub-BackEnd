package com.fabio.sarcinelli.eduhub_backend.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.model.Role;
import com.fabio.sarcinelli.eduhub_backend.repository.CredentialRepository;
import com.fabio.sarcinelli.eduhub_backend.repository.RoleRepository;

import org.springframework.boot.CommandLineRunner;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private CredentialRepository credentialRepository;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public void run(String... args) throws Exception {
    if (roleRepository.findAll().isEmpty()) {
        Role roleAdmin = new Role();
        roleAdmin.setName(ERole.ROLE_ADMIN);
        roleRepository.save(roleAdmin);

        Role roleModerator = new Role();
        roleModerator.setName(ERole.ROLE_TEACHER);
        roleRepository.save(roleModerator);

        Role roleUser = new Role();
        roleUser.setName(ERole.ROLE_USER);
        roleRepository.save(roleUser);
    }

    if (credentialRepository.findAll().isEmpty()) {
      Set<Role> roles = new HashSet<>();
      roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
      roles.add(roleRepository.findByName(ERole.ROLE_TEACHER).get());
      roles.add(roleRepository.findByName(ERole.ROLE_USER).get());

      Credential admin = new Credential();
      admin.setUsername("admin");
      admin.setPassword(encoder.encode("admin"));
      admin.setRoles(roles);
      admin.setEmail("admin@admin.com");
      credentialRepository.save(admin);
    }
  }
    
}

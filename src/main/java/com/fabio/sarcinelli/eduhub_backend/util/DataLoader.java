package com.fabio.sarcinelli.eduhub_backend.util;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.model.Users;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;
import com.fabio.sarcinelli.eduhub_backend.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private final CredentialService credentialService;
    private final UserService userService;

    public DataLoader(CredentialService credentialService, UserService userService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @Override
    public void run(String... args) throws Exception {
        Credential credential = new Credential();
        credential.setEmail("fabiosarci125@gmail.com");
        credential.setPassword("admin");
        credentialService.save(credential);

        Users users = new Users();
        users.setName("Fabio");
        users.setSurname("Sarcinelli");
        users.setBirthDate(LocalDate.of(2005, 01, 25));
        users.setRole(Role.ADMIN);
        users.setCredential(credential);
        userService.save(users);


    }
    
}

package com.fabio.sarcinelli.eduhub_backend.dto.request;

import java.time.LocalDate;
import java.util.Set;

import com.fabio.sarcinelli.eduhub_backend.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialAndUserDtoForm {

    //Credential Fields
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;

    //User Fields
    private String name;
    private String surname;
    private LocalDate birthDate;

}

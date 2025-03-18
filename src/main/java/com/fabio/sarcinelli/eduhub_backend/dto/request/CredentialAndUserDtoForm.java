package com.fabio.sarcinelli.eduhub_backend.dto.request;

import java.time.LocalDate;
import java.util.Set;

import com.fabio.sarcinelli.eduhub_backend.model.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialAndUserDtoForm {

    //Credential Fields
    private Long credentialId;

    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private Set<Role> roles;

    //User Fields
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public CredentialAndUserDtoForm(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

}

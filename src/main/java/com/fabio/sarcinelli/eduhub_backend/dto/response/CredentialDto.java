package com.fabio.sarcinelli.eduhub_backend.dto.response;

import java.util.Set;

import com.fabio.sarcinelli.eduhub_backend.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDto {
    
    private String username;
    private String email;
    private Set<Role> roles;
    
}

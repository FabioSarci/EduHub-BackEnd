package com.fabio.sarcinelli.eduhub_backend.controller.web.auth;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabio.sarcinelli.eduhub_backend.dto.request.CredentialAndUserDtoForm;
import com.fabio.sarcinelli.eduhub_backend.dto.request.SigninRequest;
import com.fabio.sarcinelli.eduhub_backend.dto.request.SignupRequest;
import com.fabio.sarcinelli.eduhub_backend.dto.response.JwtResponse;
import com.fabio.sarcinelli.eduhub_backend.dto.response.MessageResponse;
import com.fabio.sarcinelli.eduhub_backend.model.Role;
import com.fabio.sarcinelli.eduhub_backend.repository.RoleRepository;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;
import com.fabio.sarcinelli.eduhub_backend.util.ERole;
import com.fabio.sarcinelli.eduhub_backend.util.jwt.JwtUtils;
import com.fabio.sarcinelli.eduhub_backend.util.services.UserDetailsImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Controller", description = "API per l' autenticazione e la registrazione delgi utenti")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  CredentialService credentialService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
    JwtUtils jwtUtils;


    @Operation(summary = "Autentica un utente tramite username e password", description = "Restituisce il token e le credenziali")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);


        // Ottiene i dettagli dell'utente autenticato
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<ERole> roles = userDetails.getAuthorities().stream()
            .map(item -> ERole.valueOf(item.getAuthority()))
            .collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
    }


    @Operation(summary = "Registra l'utente nel database", description = "Restituisce il token e le credenziali")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Verifica se il nome utente è già in uso    
        if (credentialService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Verifica se l'email è già registrata
        if (credentialService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Creazione di un nuovo utente con credenziali codificate
        CredentialAndUserDtoForm credential = new CredentialAndUserDtoForm(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

        credential.setName(signUpRequest.getName());
        credential.setSurname(signUpRequest.getSurname());
        credential.setBirthDate(signUpRequest.getBirthDate());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // Assegna il ruolo all'utente in base alla richiesta
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "teacher":
                        Role modRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        credential.setRoles(roles);
        credentialService.save(credential);

        // Autentica l'utente appena registrato
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt,
            credential.getCredentialId(),
            credential.getUsername(),
            credential.getEmail(),
            credential.getRoles().stream().map(Role::getName).collect(Collectors.toSet())));
    }
}
package com.fabio.sarcinelli.eduhub_backend.controller.thymeleaf;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fabio.sarcinelli.eduhub_backend.dto.request.SigninRequest;
import com.fabio.sarcinelli.eduhub_backend.dto.request.SignupRequest;
import com.fabio.sarcinelli.eduhub_backend.dto.response.JwtResponse;
import com.fabio.sarcinelli.eduhub_backend.dto.response.MessageResponse;
import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.model.Role;
import com.fabio.sarcinelli.eduhub_backend.repository.CredentialRepository;
import com.fabio.sarcinelli.eduhub_backend.repository.RoleRepository;
import com.fabio.sarcinelli.eduhub_backend.util.ERole;
import com.fabio.sarcinelli.eduhub_backend.util.jwt.JwtUtils;
import com.fabio.sarcinelli.eduhub_backend.util.services.UserDetailsImpl;

import jakarta.validation.Valid;

/**
 * Controller per la gestione dell'autenticazione e della registrazione.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/thymeleaf/auth")
public class ThymeLeafAuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  /**
   * @TODO: Implementare layer service invece che usare direttamente i repository
   */
  @Autowired
  CredentialRepository credentialRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  /**
   * Gestisce il login degli utenti.
   */
  @PostMapping("/signin")
  public String authenticateUser(@Valid @RequestBody SigninRequest loginRequest, Model model) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    // Ottiene i dettagli dell'utente autenticato
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    Set<ERole> roles = userDetails.getAuthorities().stream()
        .map(item -> ERole.valueOf(item.getAuthority()))
        .collect(Collectors.toSet());

    model.addAttribute("jwt", jwt);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("roles", roles);

    return "redirect:/credential/list?token=" + jwt;
  }

  /**
   * Gestisce la registrazione di nuovi utenti.
   */
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    // Verifica se il nome utente è già in uso    
    if (credentialRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Username is already taken!"));
    }

    // Verifica se l'email è già registrata
    if (credentialRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Creazione di un nuovo utente con credenziali codificate
    Credential credential = new Credential(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

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
                case "mod":
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
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
    credentialRepository.save(credential);

    // Autentica l'utente appena registrato
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    return ResponseEntity.ok(new JwtResponse(jwt,
        credential.getId(),
        credential.getUsername(),
        credential.getEmail(),
        credential.getRoles().stream().map(Role::getName).collect(Collectors.toSet())));
  }
}
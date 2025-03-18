package com.fabio.sarcinelli.eduhub_backend.controller.web;

import com.fabio.sarcinelli.eduhub_backend.dto.request.CredentialAndUserDtoForm;
import com.fabio.sarcinelli.eduhub_backend.dto.response.CredentialDto;
import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credential")
@Tag(name = "Credential Controller", description = "API per la gestione delle credenziali")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Operation(summary = "Trova una credenziale per ID", description = "Restituisce una credenziale dato il suo ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CredentialDto> findById(@PathVariable Long id) {
        try {
            CredentialDto credential = credentialService.findById(id);
            if (credential != null) {
                return ResponseEntity.ok(credential);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
           return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Trova una credenziale per email", description = "Restituisce una credenziale dato il suo indirizzo email")
    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CredentialDto> findByEmail(@PathVariable String email) {
        CredentialDto credential = credentialService.findByEmail(email);
        if (credential != null) {
            return ResponseEntity.ok(credential);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Trova tutte le credenziali", description = "Restituisce tutte le credenziali presenti nel sistema")
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Iterable<CredentialDto>> findAll() {
        Iterable<CredentialDto> credentials = credentialService.findAll();
        return ResponseEntity.ok(credentials);
    }

    @Operation(summary = "Verifica se un username esiste", description = "Controlla se un username è già presente nel sistema")
    @GetMapping("/exist-username/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> existByUsername(@PathVariable String username) {
        Boolean exist = credentialService.existsByUsername(username); // Corretto
        if (exist) {
            return ResponseEntity.ok(exist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Verifica se un'email esiste", description = "Controlla se un'email è già presente nel sistema")
    @GetMapping("/exist-email/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> existByEmail(@PathVariable String email) {
        Boolean exist = credentialService.existsByEmail(email);
        if (exist) {
            return ResponseEntity.ok(exist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva una nuova credenziale", description = "Crea una nuova credenziale nel sistema")
    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CredentialAndUserDtoForm> save(@RequestBody CredentialAndUserDtoForm credential) {
        CredentialAndUserDtoForm credentialSaved = credentialService.save(credential);
        return ResponseEntity.ok().body(credentialSaved);
    }

    @Operation(summary = "Aggiorna una credenziale", description = "Aggiorna una credenziale esistente")
    @PutMapping
    public ResponseEntity<CredentialDto> update(@RequestBody Credential credential) {
        CredentialDto credentialUpdated = credentialService.update(credential);
        return ResponseEntity.ok(credentialUpdated);
    }

    @Operation(summary = "Elimina una credenziale", description = "Elimina una credenziale esistente")
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@RequestBody Credential credential) {
        try{
            credentialService.delete(credential);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Elimina una credenziale per ID", description = "Elimina una credenziale dato il suo ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            credentialService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

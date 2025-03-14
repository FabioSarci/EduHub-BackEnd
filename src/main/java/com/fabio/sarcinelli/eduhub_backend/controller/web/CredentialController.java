package com.fabio.sarcinelli.eduhub_backend.controller.web;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping("/{id}")
    public ResponseEntity<Credential> findById(@PathVariable Long id){

        Credential credential = credentialService.findById(id).orElse(null);
        if (credential != null){
            return ResponseEntity.ok(credential);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Credential> findByEmail(@PathVariable String email){

        Credential credential = credentialService.findByEmail(email);
        if(credential != null){
            return ResponseEntity.ok(credential);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Credential>> findAll(){

        Iterable<Credential> credentials = credentialService.findAll();
        return ResponseEntity.ok(credentials);
    }

    @GetMapping("/exist-username/{username}")
    public ResponseEntity<Boolean> existByUsername(@PathVariable String username){

        Boolean exist = credentialService.existsByEmail(username);
        if(exist){
            return ResponseEntity.ok(exist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exist-email/{email}")
    public ResponseEntity<Boolean> existByEmail(@PathVariable String email){

        Boolean exist = credentialService.existsByEmail(email);
        if(exist){
            return ResponseEntity.ok(exist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Credential> save(@RequestBody Credential credential){

        Credential credentialSaved = credentialService.save(credential);
        return ResponseEntity.ok(credentialSaved);
    }

    @PutMapping
    public ResponseEntity<Credential> update(@RequestBody Credential credential){

        Credential credentialUpdated = credentialService.update(credential);
        return ResponseEntity.ok(credentialUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Credential credential){

        credentialService.delete(credential);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        credentialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

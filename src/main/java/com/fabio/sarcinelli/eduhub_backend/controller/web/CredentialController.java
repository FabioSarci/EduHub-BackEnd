package com.fabio.sarcinelli.eduhub_backend.controller.web;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<Credential> save(@RequestBody Credential credential){

        Credential credentialSaved = credentialService.save(credential);
        return ResponseEntity.ok(credentialSaved);
    }
}

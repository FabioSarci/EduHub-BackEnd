package com.fabio.sarcinelli.eduhub_backend.controller.web;

import java.util.List;

import com.fabio.sarcinelli.eduhub_backend.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabio.sarcinelli.eduhub_backend.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        Users users = userService.findById(id).orElse(null);

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<Users> findByEmail(@PathVariable String email) {
        Users users = userService.findUserByEmail(email);

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users users) {
        Users newUsers = userService.save(users);
        return ResponseEntity.ok(newUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users users) {
        Users updatedStudent = userService.update(users);

        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        userService.deleteById(id);
        String message = "Studente con ID " + id + " eliminato con successo.";

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
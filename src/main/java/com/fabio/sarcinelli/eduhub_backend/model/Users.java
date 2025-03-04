package com.fabio.sarcinelli.eduhub_backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fabio.sarcinelli.eduhub_backend.util.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private Role role;

    @OneToOne
    Credential credential;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();


}

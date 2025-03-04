package com.fabio.sarcinelli.eduhub_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String section;
    private String subject;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Users> users = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();
}

package com.infobasic.sviluppo_sowftare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private LocalDate birthdate;

    @OneToOne
    private Credential credential;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserRole role;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Presence> presences = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserQuiz> quizzes = new ArrayList<>();


}

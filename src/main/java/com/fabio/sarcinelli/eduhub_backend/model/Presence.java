package com.fabio.sarcinelli.eduhub_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean present;

    @ManyToOne
    private Lesson lesson;

    @ManyToOne
    private Users users;
    
}

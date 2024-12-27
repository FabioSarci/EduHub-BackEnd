package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @NonNull
    private int id;
    @NonNull
    private String topic;
    @NonNull
    private String description;
    @NonNull
    private LocalDateTime date;
    private Course course;

}

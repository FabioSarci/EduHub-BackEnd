package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @NonNull
    private int id;
    @NonNull
    private String title;
    @NonNull
    private LocalDateTime publishedAt;
    @NonNull
    private Course course;
}

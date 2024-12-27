package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Presence {

    @NonNull
    private int id;
    private Lesson lesson;
    private User user;
    private LocalDateTime date;
    private boolean isPresent;
}

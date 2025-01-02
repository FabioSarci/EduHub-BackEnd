package com.infobasic.sviluppo_sowftare.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Presence {

    @NonNull
    private int id;
    private int lessonId;
    private int userId;
    private boolean isPresent;
}

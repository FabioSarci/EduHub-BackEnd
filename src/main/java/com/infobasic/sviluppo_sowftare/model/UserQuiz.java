package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuiz {

    @NonNull
    private int id;
    private Quiz quiz;
    private User user;
    private LocalDateTime completedAt;
}
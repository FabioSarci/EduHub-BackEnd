package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @NonNull
    private int id;
    @NonNull
    private String text;
    @NonNull
    private boolean isCorrect;
    private Question question;

}

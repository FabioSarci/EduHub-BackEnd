package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {

    @NonNull
    private int id;
    private int questionId;
    private int userQuizId;
    private int answerId;
}

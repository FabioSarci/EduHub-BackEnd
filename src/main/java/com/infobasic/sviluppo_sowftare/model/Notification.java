package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @NonNull
    private int id;
    @NonNull
    private String object;
    @NonNull
    private String body;
    @NonNull
    private int userId;
}

package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @NonNull
    private int id;
    @NonNull
    private String coursename;
    @NonNull
    private String section;
    @NonNull
    private String subject;
}

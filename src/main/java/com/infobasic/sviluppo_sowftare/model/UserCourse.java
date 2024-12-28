package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse {

    @NonNull
    private int id;
    @NonNull
    private int courseId;
    @NonNull
    private int userId;
}

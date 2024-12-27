package com.infobasic.sviluppo_sowftare.model;

import com.infobasic.sviluppo_sowftare.utility.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private LocalDate birthdate;
    @NonNull
    private UserType role;
}

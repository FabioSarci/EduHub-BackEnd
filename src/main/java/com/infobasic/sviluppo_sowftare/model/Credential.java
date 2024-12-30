package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    @NonNull
    private int id;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private int userid;
}

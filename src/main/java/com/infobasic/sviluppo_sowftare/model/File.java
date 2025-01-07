package com.infobasic.sviluppo_sowftare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @NonNull
    int id;
    String filename;
    String path;
    int userId;
    int courseId;
    String title;
    LocalDate createdAt;
    LocalDate updatedAt;
    String content;
}

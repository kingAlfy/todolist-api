package com.alfonsoravelogil.task.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private Long id;

    private String name;
    private String description;
    private Date createdAt;

    public NoteDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

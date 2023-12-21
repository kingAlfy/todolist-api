package com.alfonsoravelogil.task.dto;

import com.alfonsoravelogil.task.config.markers.RestNotesValidationGroup;
import com.alfonsoravelogil.task.config.markers.UpdateNotesValidationGroup;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @GroupSequence({NoteDTO.class})
public class NoteDTO {

    @Null
    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 3, message = "Tittle must be at least 3 characters long")
    private String title;

    @NotNull(message = "Content is required")
    private String content;

    @Null
    private Date createdAt;

    public NoteDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

package com.alfonsoravelogil.task.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
// @allArgs
public class NoteNotFoundException extends RuntimeException{
    private String message = "Note not exist";
    private HttpStatus status = HttpStatus.NOT_FOUND;

    /*public NoteNotFoundException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }*/


}
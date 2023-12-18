package com.alfonsoravelogil.task.controller;


import com.alfonsoravelogil.task.dto.ErrorDTO;
import com.alfonsoravelogil.task.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(value = NoteNotFoundException.class)
    public ResponseEntity<ErrorDTO> noteNotFoundHandler(NoteNotFoundException ex, WebRequest request) {

        // ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), ex.getStatus().value(), ex.getStatus(), "Note not exist", request.getDescription(false).substring(4));

        ErrorDTO errorDTO = new ErrorDTO(ex.getStatus().value(), ex.getStatus(), ex.getMessage(), request.getDescription(false).substring(4));

        return new ResponseEntity<>(errorDTO, ex.getStatus());
    }


}

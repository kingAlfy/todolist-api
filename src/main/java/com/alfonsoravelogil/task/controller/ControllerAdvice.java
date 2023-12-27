package com.alfonsoravelogil.task.controller;


import com.alfonsoravelogil.task.dto.ErrorDTO;
import com.alfonsoravelogil.task.dto.ValidationErrorDTO;
import com.alfonsoravelogil.task.exception.NoteNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Validation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(value = NoteNotFoundException.class)
    public ResponseEntity<ErrorDTO> noteNotFoundHandler(NoteNotFoundException ex, WebRequest request) {

        // ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), ex.getStatus().value(), ex.getStatus(), "Note not exist", request.getDescription(false).substring(4));

        ErrorDTO errorDTO = new ErrorDTO(ex.getStatus().value(), ex.getStatus(), ex.getMessage(), request.getDescription(false).substring(4));

        return new ResponseEntity<>(errorDTO, ex.getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorDTO> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        // ex.getBindingResult().getAllErrors().toString(), ex.getMessages() ex.getFieldError().toString()

        // ex.getDetailMessageArguments().toString()

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        /*JSONObject jsonWithErrors = new JSONObject();
        JSONArray arrayOfErrors = new JSONArray();


        for (FieldError fieldError : fieldErrors) {
            JSONObject jsonWithError = new JSONObject();

            jsonWithError.put("Field", fieldError.getField());
            jsonWithError.put("Message", fieldError.getDefaultMessage());

            arrayOfErrors.put(jsonWithError);
        }

        jsonWithErrors.put("Validation errors", arrayOfErrors);*/

        Map<String, Object> validationErrors = new HashMap<>();
        List<Map<String, String>> errorList = fieldErrors.stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("Field", fieldError.getField());
                    errorMap.put("Message", fieldError.getDefaultMessage());
                    return errorMap;
                })
                .toList();

        validationErrors.put("validationErrors", errorList);

        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO(ex.getStatusCode().value(), ex.getStatusCode(), validationErrors , request.getServletPath(), request.getMethod());

        return new ResponseEntity<>(validationErrorDTO, ex.getStatusCode());
    }


}


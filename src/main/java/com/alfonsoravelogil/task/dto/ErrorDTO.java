package com.alfonsoravelogil.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private HttpStatus error;
    private String message;
    private String path;

}

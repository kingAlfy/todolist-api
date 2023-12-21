package com.alfonsoravelogil.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDTO {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private HttpStatusCode error;
    private Map<String, Object> message;
    private String path;
    private String method;

}

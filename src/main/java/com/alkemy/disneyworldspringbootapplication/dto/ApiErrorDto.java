package com.alkemy.disneyworldspringbootapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ApiErrorDto {
    private String message;
    private List<String> errors;
    private HttpStatus status;
}

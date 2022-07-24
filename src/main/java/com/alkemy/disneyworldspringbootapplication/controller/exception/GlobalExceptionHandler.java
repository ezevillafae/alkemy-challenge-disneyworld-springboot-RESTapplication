package com.alkemy.disneyworldspringbootapplication.controller.exception;

import com.alkemy.disneyworldspringbootapplication.dto.ApiErrorDto;
import com.alkemy.disneyworldspringbootapplication.exception.CharacterNotFound;
import com.alkemy.disneyworldspringbootapplication.exception.FilmNotFound;
import com.alkemy.disneyworldspringbootapplication.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ParamNotFound.class)
    protected ResponseEntity<Object> handleParamNotFoundException(RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = ApiErrorDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .errors(List.of("Param Not Found"))
                .build();
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = CharacterNotFound.class)
    protected ResponseEntity<Object> handleCharacterNotFoundException(RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = ApiErrorDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .errors(List.of("Character Not Found"))
                .build();
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = FilmNotFound.class)
    protected ResponseEntity<Object> handleFilmNotFoundException(RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = ApiErrorDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .errors(List.of("Film Not Found"))
                .build();
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.add(String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage())));

        ex.getBindingResult().getGlobalErrors()
                .forEach(objectError -> errors.add(String.format("%s : %s", objectError.getObjectName(), objectError.getDefaultMessage())));

        ApiErrorDto apiErrorDto = ApiErrorDto.builder()
                .message(ex.getLocalizedMessage())
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return handleExceptionInternal(ex, apiErrorDto, headers, HttpStatus.BAD_REQUEST, request);

    }
}

package com.alkemy.disneyworldspringbootapplication.exception;

public class FilmNotFound extends RuntimeException{
    public FilmNotFound(Long id) {
        super(String.format("Film %s not found", id));
    }
}

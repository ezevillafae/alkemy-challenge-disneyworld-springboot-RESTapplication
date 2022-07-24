package com.alkemy.disneyworldspringbootapplication.exception;

public class CharacterNotFound extends RuntimeException {

    public CharacterNotFound(Long id) {
        super(String.format("character %s not found", id));
    }
}

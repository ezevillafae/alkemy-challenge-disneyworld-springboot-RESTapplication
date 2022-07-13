package com.alkemy.disneyworldspringbootapplication.exception;

public class ParamNotFound extends RuntimeException{

    public ParamNotFound(String message) {
        super(message);
    }
}

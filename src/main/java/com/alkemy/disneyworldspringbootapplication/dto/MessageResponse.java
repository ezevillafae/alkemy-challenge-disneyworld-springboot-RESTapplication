package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.Data;

@Data
public class MessageResponse {
    private String message;
    public MessageResponse(String s) {
        this.message = s;
    }
}

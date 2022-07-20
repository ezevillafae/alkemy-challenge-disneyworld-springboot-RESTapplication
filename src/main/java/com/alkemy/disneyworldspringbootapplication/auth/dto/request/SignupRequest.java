package com.alkemy.disneyworldspringbootapplication.auth.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}


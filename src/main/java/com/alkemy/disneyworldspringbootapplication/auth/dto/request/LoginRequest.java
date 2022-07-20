package com.alkemy.disneyworldspringbootapplication.auth.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}

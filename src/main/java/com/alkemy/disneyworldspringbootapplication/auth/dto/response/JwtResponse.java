package com.alkemy.disneyworldspringbootapplication.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String token;
    private String type;
    private String username;
}

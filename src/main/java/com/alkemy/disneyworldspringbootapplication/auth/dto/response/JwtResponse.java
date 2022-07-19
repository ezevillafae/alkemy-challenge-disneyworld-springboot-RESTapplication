package com.alkemy.disneyworldspringbootapplication.auth.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}

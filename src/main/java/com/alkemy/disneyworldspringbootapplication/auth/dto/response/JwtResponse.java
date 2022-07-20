package com.alkemy.disneyworldspringbootapplication.auth.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
}

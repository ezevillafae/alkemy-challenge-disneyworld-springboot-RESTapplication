package com.alkemy.disneyworldspringbootapplication.auth.service;

import com.alkemy.disneyworldspringbootapplication.auth.dto.request.LoginRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.response.JwtResponse;
import org.springframework.security.core.AuthenticationException;

public interface LoginService {
    JwtResponse authenticate(LoginRequest loginRequest) throws AuthenticationException;
}

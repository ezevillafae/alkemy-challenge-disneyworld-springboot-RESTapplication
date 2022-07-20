package com.alkemy.disneyworldspringbootapplication.auth.service;

import com.alkemy.disneyworldspringbootapplication.auth.dto.request.LoginRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.response.JwtResponse;

public interface LoginService {
    JwtResponse authenticate(LoginRequest loginRequest);
}

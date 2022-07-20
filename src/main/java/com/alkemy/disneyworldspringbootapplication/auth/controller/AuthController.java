package com.alkemy.disneyworldspringbootapplication.auth.controller;

import com.alkemy.disneyworldspringbootapplication.auth.dto.request.LoginRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.request.SignupRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.response.JwtResponse;
import com.alkemy.disneyworldspringbootapplication.auth.service.LoginService;
import com.alkemy.disneyworldspringbootapplication.auth.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = loginService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(registerService.register(signupRequest));
    }
}

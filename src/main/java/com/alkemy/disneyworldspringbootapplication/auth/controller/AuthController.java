package com.alkemy.disneyworldspringbootapplication.auth.controller;

import com.alkemy.disneyworldspringbootapplication.auth.dto.UserDetailsImpl;
import com.alkemy.disneyworldspringbootapplication.auth.dto.request.LoginRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.request.SignupRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.response.JwtResponse;
import com.alkemy.disneyworldspringbootapplication.auth.entity.UserEntity;
import com.alkemy.disneyworldspringbootapplication.auth.exception.MessageResponse;
import com.alkemy.disneyworldspringbootapplication.auth.repository.UserRepository;
import com.alkemy.disneyworldspringbootapplication.auth.shared.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(
                JwtResponse.builder()
                        .username(userDetails.getUsername())
                        .token(jwt)
                        .id(userDetails.getId())
                        .build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("user register successful"));
    }
}

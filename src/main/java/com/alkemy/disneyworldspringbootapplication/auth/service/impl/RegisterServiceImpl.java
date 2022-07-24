package com.alkemy.disneyworldspringbootapplication.auth.service.impl;

import com.alkemy.disneyworldspringbootapplication.auth.dto.request.SignupRequest;
import com.alkemy.disneyworldspringbootapplication.auth.entity.UserEntity;
import com.alkemy.disneyworldspringbootapplication.auth.repository.UserRepository;
import com.alkemy.disneyworldspringbootapplication.auth.service.RegisterService;
import com.alkemy.disneyworldspringbootapplication.dto.MessageResponse;
import com.alkemy.disneyworldspringbootapplication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailService emailService;

    @Override
    public MessageResponse register(SignupRequest signupRequest) {
        if(repository.existsByUsername(signupRequest.getUsername()))
            return new MessageResponse("Error: Username is already taken!");

        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        repository.save(user);

        emailService.sendWelcomeEmailTo(signupRequest.getUsername());

        return new MessageResponse("user register successful");
    }
}

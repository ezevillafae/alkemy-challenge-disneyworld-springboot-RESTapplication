package com.alkemy.disneyworldspringbootapplication.auth.service.impl;

import com.alkemy.disneyworldspringbootapplication.auth.dto.UserDetailsImpl;
import com.alkemy.disneyworldspringbootapplication.auth.dto.request.LoginRequest;
import com.alkemy.disneyworldspringbootapplication.auth.dto.response.JwtResponse;
import com.alkemy.disneyworldspringbootapplication.auth.service.LoginService;
import com.alkemy.disneyworldspringbootapplication.auth.shared.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final String JWT_TYPE = "Bearer";

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse authenticate(LoginRequest loginRequest) throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return new JwtResponse(userDetails.getId(), jwt, JWT_TYPE, userDetails.getUsername());
        } catch (Exception e) {
            throw  new BadCredentialsException("Username or password incorrect");
        }
    }
}

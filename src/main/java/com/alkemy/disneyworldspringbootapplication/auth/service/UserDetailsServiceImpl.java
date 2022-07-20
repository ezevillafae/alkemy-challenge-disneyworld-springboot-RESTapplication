package com.alkemy.disneyworldspringbootapplication.auth.service;

import com.alkemy.disneyworldspringbootapplication.auth.dto.UserDetailsImpl;
import com.alkemy.disneyworldspringbootapplication.auth.entity.UserEntity;
import com.alkemy.disneyworldspringbootapplication.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return UserDetailsImpl.build(user);
    }
}

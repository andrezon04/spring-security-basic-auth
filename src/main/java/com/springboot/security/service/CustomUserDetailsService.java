package com.springboot.security.service;

import com.springboot.security.repositories.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.springboot.security.entities.User user = userRepo.findByEmail(email);

        return User.builder()
                .username(user.getEmail())
                .password(user.getPass())
                .roles(user.getRole().name().replace("ROLE_",""))
                .build();
    }
}
package com.springboot.security.service;

import com.springboot.security.dto.user.ReqUserDTO;
import com.springboot.security.entities.User;
import com.springboot.security.entities.enums.Role;
import com.springboot.security.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;


    public User createUser(ReqUserDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setPass(passwordEncoder.encode(dto.getPass()));
        user.setEmail(dto.getEmail());
        user.setRole(Role.ROLE_USER);

        userRepo.save(user);

        return user;
    }
}
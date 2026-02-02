package com.azad.BlogManagementSystem.service;


import com.azad.BlogManagementSystem.dto.JwtResponse;
import com.azad.BlogManagementSystem.dto.LoginRequest;
import com.azad.BlogManagementSystem.dto.RegisterRequest;
import com.azad.BlogManagementSystem.entity.User;
import com.azad.BlogManagementSystem.exception.UnauthorizedActionException;
import com.azad.BlogManagementSystem.exception.UserAlreadyExistsException;
import com.azad.BlogManagementSystem.repository.UserRepository;
import com.azad.BlogManagementSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new UserAlreadyExistsException("Username taken");
        if (userRepository.existsByEmail(request.getEmail())) throw new UserAlreadyExistsException("Email taken");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully";
    }

    public JwtResponse login(LoginRequest request) {


        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtil.generateToken(auth);
        return new JwtResponse(jwt, "Bearer", request.getUsername(), List.of("ROLE_USER"));
    }

}

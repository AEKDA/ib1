package com.aekda.lab1.controller;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aekda.lab1.controller.dto.LoginRequest;
import com.aekda.lab1.controller.dto.RegisterRequest;
import com.aekda.lab1.entity.User;
import com.aekda.lab1.security.JwtUtil;
import com.aekda.lab1.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> u = userService.findByUsername(request.getUsername());
        if (u.isPresent() && userService.checkPassword(request.getPassword(), u.get().getPassword())) {
            String token = jwtUtil.generateToken(u.get().getUsername());
            return ResponseEntity.ok().body(
                    java.util.Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(java.util.Map.of("error", "Username already exists"));
        }

        User created = userService.register(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(created.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(java.util.Map.of(
                        "message", "User registered successfully",
                        "token", token));
    }
}
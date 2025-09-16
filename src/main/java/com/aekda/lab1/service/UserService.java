package com.aekda.lab1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aekda.lab1.entity.User;
import com.aekda.lab1.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public User  register(String username, String rawPassword){
        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        return repo.save(u);
    }

    public Optional<User> findByUsername(String username){
        return repo.findByUsername(username);
    }

    public boolean checkPassword(String raw, String hash){
        return encoder.matches(raw, hash);
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}

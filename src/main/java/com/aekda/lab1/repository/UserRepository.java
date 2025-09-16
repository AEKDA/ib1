package com.aekda.lab1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aekda.lab1.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
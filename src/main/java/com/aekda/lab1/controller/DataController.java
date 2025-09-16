package com.aekda.lab1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aekda.lab1.controller.dto.DataResponse;
import com.aekda.lab1.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DataController {
    private final UserService userService;
    @GetMapping("/data")
    public List<DataResponse> getData(){
        return userService.findAll().stream().map(t -> new DataResponse(t.getId(), t.getUsername())).collect(Collectors.toList());
    }
}
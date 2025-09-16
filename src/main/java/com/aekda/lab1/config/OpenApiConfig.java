package com.aekda.lab1.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(
    name = "bearerAuth",             // имя схемы
    type = SecuritySchemeType.HTTP,  // HTTP authentication
    scheme = "bearer",               // Bearer token
    bearerFormat = "JWT"             // формат токена
)
public class OpenApiConfig {
}
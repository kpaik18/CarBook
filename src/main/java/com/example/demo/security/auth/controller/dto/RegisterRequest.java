package com.example.demo.security.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotNull @Size(min = 3) String firstName,
        @NotNull @Size(min = 3) String lastName,
        @NotNull @Email String email,
        @NotNull @Size(min = 3) String password
) {
}

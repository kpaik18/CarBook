package com.example.demo.security.auth.controller.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(@NotNull String username,
                       @NotNull String password) {
}

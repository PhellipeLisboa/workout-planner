package com.phellipe.workoutplanner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        String name,
        @Email
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password
)
{}

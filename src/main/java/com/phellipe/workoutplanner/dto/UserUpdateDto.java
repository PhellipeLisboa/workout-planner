package com.phellipe.workoutplanner.dto;

public record UserUpdateDto(
        String name,
        String email,
        String password
)
{}

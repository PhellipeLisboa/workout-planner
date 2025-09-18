package com.phellipe.workoutplanner.dto;

public record UserRequestDto(
   String name,
   String email,
   String password
) {}

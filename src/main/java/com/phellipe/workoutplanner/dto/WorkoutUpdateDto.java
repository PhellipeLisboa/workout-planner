package com.phellipe.workoutplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkoutUpdateDto(

        String name,
        @Size(max = 500)
        String description,
        List<Long> exercisesId
) {}

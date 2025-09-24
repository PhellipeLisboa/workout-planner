package com.phellipe.workoutplanner.dto;

import java.util.List;

public record WorkoutResponseDto(

        Long id,
        String name,
        String description,
        Long userId,
        String userName,
        List<ExerciseResponseDto> exercises
) {}

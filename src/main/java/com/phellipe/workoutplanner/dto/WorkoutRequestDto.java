package com.phellipe.workoutplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkoutRequestDto(

        @NotBlank(message = "O nome do treino é obrigatório")
        String name,
        @Size(max = 500)
        String description,
        @NotEmpty(message = "É necessário informar pelo menos um exercício")
        List<Long> exercisesId
) {}

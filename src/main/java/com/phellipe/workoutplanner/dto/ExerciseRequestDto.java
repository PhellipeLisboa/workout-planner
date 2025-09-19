package com.phellipe.workoutplanner.dto;

import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ExerciseRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String name,
        @Size(max = 500)
        @NotBlank(message = "A descrição é obrigatória")
        String description,

        @NotEmpty(message = "É necessário informar pelo menos um grupo muscular")
        List<MuscleGroup> muscleGroups,
        @NotNull(message = "O campo equipamento é obrigatório")
        Equipment equipment

) {}

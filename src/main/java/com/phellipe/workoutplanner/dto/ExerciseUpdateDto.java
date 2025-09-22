package com.phellipe.workoutplanner.dto;

import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ExerciseUpdateDto(


        String name,
        @Size(max = 500)
        String description,
        List<MuscleGroup> muscleGroups,
        Equipment equipment

) {}

package com.phellipe.workoutplanner.dto;

import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.MuscleGroup;

import java.util.List;

public record ExerciseResponseDto(
        Long id,
        String name,
        String description,
        List<MuscleGroup> muscleGroups,
        Equipment equipment
) {}

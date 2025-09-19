package com.phellipe.workoutplanner.mapper;

import com.phellipe.workoutplanner.dto.ExerciseRequestDto;
import com.phellipe.workoutplanner.dto.ExerciseResponseDto;
import com.phellipe.workoutplanner.entity.Exercise;

public class ExerciseMapper {

    public static Exercise toEntity(ExerciseRequestDto dto) {

        return Exercise.builder()
                .name(dto.name())
                .description(dto.description())
                .muscleGroups(dto.muscleGroups())
                .equipment(dto.equipment())
                .build();

    }

    public static ExerciseResponseDto toDto(Exercise exercise) {

        return new ExerciseResponseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getMuscleGroups(),
                exercise.getEquipment()
                );

    }

}

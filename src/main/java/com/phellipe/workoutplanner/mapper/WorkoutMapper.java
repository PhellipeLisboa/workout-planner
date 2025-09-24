package com.phellipe.workoutplanner.mapper;

import com.phellipe.workoutplanner.dto.WorkoutResponseDto;
import com.phellipe.workoutplanner.entity.Workout;

public class WorkoutMapper {

    public static WorkoutResponseDto toDto(Workout workout) {

        return new WorkoutResponseDto(
                workout.getId(),
                workout.getName(),
                workout.getDescription(),
                workout.getUser().getId(),
                workout.getUser().getName(),
                workout.getExercises()
                        .stream()
                        .map(ExerciseMapper::toDto)
                        .toList()
        );

    }

}

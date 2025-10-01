package com.phellipe.workoutplanner.service;

import com.phellipe.workoutplanner.dto.WorkoutRequestDto;
import com.phellipe.workoutplanner.dto.WorkoutResponseDto;
import com.phellipe.workoutplanner.dto.WorkoutUpdateDto;
import com.phellipe.workoutplanner.entity.Exercise;
import com.phellipe.workoutplanner.entity.User;
import com.phellipe.workoutplanner.entity.Workout;
import com.phellipe.workoutplanner.exception.ExerciseNotFoundException;
import com.phellipe.workoutplanner.exception.NoWorkoutsFoundException;
import com.phellipe.workoutplanner.exception.UserNotFoundException;
import com.phellipe.workoutplanner.exception.WorkoutNotFoundException;
import com.phellipe.workoutplanner.mapper.UserMapper;
import com.phellipe.workoutplanner.mapper.WorkoutMapper;
import com.phellipe.workoutplanner.repository.ExerciseRepository;
import com.phellipe.workoutplanner.repository.UserRepository;
import com.phellipe.workoutplanner.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutResponseDto save(Long userId, WorkoutRequestDto dto) {

        User userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );

        List<Exercise> exercises = dto.exercisesId()
                .stream()
                .map(this::findExerciseById)
                .collect(Collectors.toList());

        Workout workoutEntity = Workout.builder()
                .name(dto.name())
                .description(dto.description())
                .user(userEntity)
                .exercises(exercises)
                .build();

        Workout savedWorkout = workoutRepository.save(workoutEntity);

        return WorkoutMapper.toDto(savedWorkout);

    }

    public WorkoutResponseDto findById(Long id) {

        Workout workout = findWorkoutById(id);

        return WorkoutMapper.toDto(workout);

    }
    public List<WorkoutResponseDto> findByUser(Long userId) {

        User user = findUserById(userId);

        List<Workout> userWorkouts = user.getWorkouts();

        if (userWorkouts == null || userWorkouts.isEmpty()) {
            throw new NoWorkoutsFoundException(userId);
        }

        return userWorkouts
                .stream()
                .map(WorkoutMapper::toDto)
                .toList();

    }

    public List<WorkoutResponseDto> findAll() {

        List<Workout> workouts = workoutRepository.findAll();

        if (workouts.isEmpty()) {
            throw new NoWorkoutsFoundException();
        }

        return workouts.stream()
                .map(WorkoutMapper::toDto)
                .toList();

    }


    public WorkoutResponseDto update(Long id, WorkoutUpdateDto dto) {

        Workout workoutEntity = findWorkoutById(id);

        if (dto.name() != null) workoutEntity.setName(dto.name());
        if (dto.description() != null) workoutEntity.setDescription(dto.description());
        if (dto.exercisesId() != null && !dto.exercisesId().isEmpty()) workoutEntity.setExercises(
                dto.exercisesId()
                        .stream()
                        .map(this::findExerciseById)
                        .collect(Collectors.toList())
        );

        Workout savedWorkout = workoutRepository.save(workoutEntity);

        return WorkoutMapper.toDto(savedWorkout);

    }

    public void delete(Long id) {

        if (!workoutRepository.existsById(id)) {
            throw new WorkoutNotFoundException(id);
        }

        workoutRepository.deleteById(id);

    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }

    private Exercise findExerciseById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(
                () -> new ExerciseNotFoundException(id)
        );
    }

    private Workout findWorkoutById(Long id) {
        return workoutRepository.findById(id).orElseThrow(
                () -> new WorkoutNotFoundException(id)
        );
    }

}

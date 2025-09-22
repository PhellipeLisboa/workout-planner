package com.phellipe.workoutplanner.service;

import com.phellipe.workoutplanner.dto.ExerciseRequestDto;
import com.phellipe.workoutplanner.dto.ExerciseResponseDto;
import com.phellipe.workoutplanner.dto.ExerciseUpdateDto;
import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.Exercise;
import com.phellipe.workoutplanner.entity.MuscleGroup;
import com.phellipe.workoutplanner.mapper.ExerciseMapper;
import com.phellipe.workoutplanner.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseResponseDto save(ExerciseRequestDto dto) {

        Exercise exercise = ExerciseMapper.toEntity(dto);

        Exercise savedExercise = exerciseRepository.save(exercise);

        return ExerciseMapper.toDto(savedExercise);

    }

    public ExerciseResponseDto findById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Exercício não encontrado.")
        );

        return ExerciseMapper.toDto(exercise);
    }

    public List<ExerciseResponseDto> findAll() {

        return exerciseRepository.findAll()
                .stream()
                .map(ExerciseMapper::toDto)
                .toList();

    }

    public List<ExerciseResponseDto> findByMuscleGroup(MuscleGroup muscleGroup) {

        List<Exercise> exercisesByMuscleGroup = exerciseRepository.findByMuscleGroupsContaining(muscleGroup);

        if (exercisesByMuscleGroup.isEmpty()) throw new RuntimeException("Não existe nenhum exercício para o grupo muscular " + muscleGroup);

        return exercisesByMuscleGroup.stream()
                .map(ExerciseMapper::toDto)
                .toList();

    }

    public List<ExerciseResponseDto> findByNameContains(String keyword) {

        List<Exercise> exercisesByNameContains = exerciseRepository.findByNameContainingIgnoreCase(keyword);

        if (exercisesByNameContains.isEmpty()) throw new RuntimeException("Não existe nenhum exercício encontrado para a pesquisa: " + keyword);

        return exercisesByNameContains.stream()
                .map(ExerciseMapper::toDto)
                .toList();

    }

    public List<ExerciseResponseDto> findByMuscleGroupAndEquipment(MuscleGroup muscleGroup, Equipment equipment) {

        List<Exercise> exercisesByMuscleGroupAndEquipment = exerciseRepository.findByMuscleGroupsContainingAndEquipment(muscleGroup, equipment);

        if (exercisesByMuscleGroupAndEquipment.isEmpty()) throw new RuntimeException("Nenhum exercício encontrado para "
                + muscleGroup + " utilizando " + equipment);

        return exercisesByMuscleGroupAndEquipment.stream()
                .map(ExerciseMapper::toDto)
                .toList();

    }

    public ExerciseResponseDto update(Long id, ExerciseUpdateDto dto) {

        Exercise exerciseEntity = findEntityById(id);

        if (dto.name() != null) exerciseEntity.setName(dto.name());
        if (dto.description() != null) exerciseEntity.setDescription(dto.description());
        if (dto.muscleGroups() != null && !dto.muscleGroups().isEmpty()) exerciseEntity.setMuscleGroups(dto.muscleGroups());
        if (dto.equipment() != null) exerciseEntity.setEquipment(dto.equipment());

        Exercise savedExercise = exerciseRepository.save(exerciseEntity);

        return ExerciseMapper.toDto(savedExercise);

    }

    public void deleteById(Long id) {

        if (!exerciseRepository.existsById(id)) {
            throw new RuntimeException("Exercício não encontrado.");
        }
        exerciseRepository.deleteById(id);

    }


    private Exercise findEntityById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Exercício não encontrado.")
        );
    }

}
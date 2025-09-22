package com.phellipe.workoutplanner.controller;

import com.phellipe.workoutplanner.dto.ExerciseRequestDto;
import com.phellipe.workoutplanner.dto.ExerciseResponseDto;
import com.phellipe.workoutplanner.dto.ExerciseUpdateDto;
import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.MuscleGroup;
import com.phellipe.workoutplanner.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponseDto> save(@RequestBody @Valid ExerciseRequestDto dto) {

        ExerciseResponseDto savedExercise = exerciseService.save(dto);
        URI location = URI.create("/exercises/" + savedExercise.id());
        return ResponseEntity.created(location).body(savedExercise);

    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDto>> findAll(
            @RequestParam(required = false) MuscleGroup muscleGroup,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Equipment equipment
            ) {

        List<ExerciseResponseDto> result;

        if (muscleGroup != null && equipment != null) {
            result = exerciseService.findByMuscleGroupAndEquipment(muscleGroup, equipment);
        } else if (muscleGroup != null) {
            result = exerciseService.findByMuscleGroup(muscleGroup);
        } else if (name != null) {
            result = exerciseService.findByNameContains(name);
        } else if (equipment != null) {
            return ResponseEntity.badRequest().build();
        } else {
            result = exerciseService.findAll();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(exerciseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> update(@PathVariable Long id ,@RequestBody @Valid ExerciseUpdateDto dto) {

        ExerciseResponseDto updatedExercise = exerciseService.update(id, dto);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

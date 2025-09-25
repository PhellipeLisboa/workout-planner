package com.phellipe.workoutplanner.controller;

import com.phellipe.workoutplanner.dto.WorkoutResponseDto;
import com.phellipe.workoutplanner.dto.WorkoutUpdateDto;
import com.phellipe.workoutplanner.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> findAll() {
        return ResponseEntity.ok(workoutService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> update(@PathVariable Long id, @RequestBody @Valid WorkoutUpdateDto dto) {
        WorkoutResponseDto updatedWorkout = workoutService.update(id, dto);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.ok().build();
    }

}

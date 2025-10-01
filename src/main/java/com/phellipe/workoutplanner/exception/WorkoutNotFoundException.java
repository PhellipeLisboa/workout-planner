package com.phellipe.workoutplanner.exception;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(Long id) {
        super("Treino com id " + id + " não encontrado.");
    }

}

package com.phellipe.workoutplanner.exception;

public class ExerciseNotFoundException extends RuntimeException {

    public ExerciseNotFoundException(Long id) {
        super("Exercício com id " + id + " não encontrado.");
    }

}

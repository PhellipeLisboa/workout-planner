package com.phellipe.workoutplanner.exception;

import com.phellipe.workoutplanner.entity.Equipment;

public class ExerciseAlreadyExistsException extends RuntimeException{

    public ExerciseAlreadyExistsException(String name, Equipment equipment) {
        super("O exercício '" + name + "' com o equipamento '" + equipment + "' já existe.");
    }

}

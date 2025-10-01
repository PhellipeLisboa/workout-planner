package com.phellipe.workoutplanner.exception;

import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.MuscleGroup;

public class NoExercisesFoundException extends RuntimeException{

    public NoExercisesFoundException(MuscleGroup muscleGroup) {
        super("Nenhum exercício encontrado para o grupo muscular " + muscleGroup);
    }

    public NoExercisesFoundException(String keyword) {
        super("Nenhum exercício encontrado para a pesquisa: " + keyword);
    }

    public NoExercisesFoundException(MuscleGroup muscleGroup, Equipment equipment) {
        super("Nenhum exercício encontrado para " + muscleGroup + " utilizando " + equipment);
    }

}

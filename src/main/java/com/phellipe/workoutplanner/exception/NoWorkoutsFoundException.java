package com.phellipe.workoutplanner.exception;

public class NoWorkoutsFoundException extends RuntimeException {

    public NoWorkoutsFoundException() {
        super("Nenhum treino encontrado.");
    }

    public NoWorkoutsFoundException(Long userId) {
        super("Nenhum treino encontrado para o usuário " + userId);
    }

}

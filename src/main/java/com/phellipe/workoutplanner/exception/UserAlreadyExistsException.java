package com.phellipe.workoutplanner.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {

        super("O email já está sendo utilizado.");

    }

}

package com.phellipe.workoutplanner.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String email) {

        super("O email " + email + " já está sendo utilizado.");

    }

}

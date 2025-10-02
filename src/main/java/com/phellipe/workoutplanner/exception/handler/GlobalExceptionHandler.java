package com.phellipe.workoutplanner.exception.handler;

import com.phellipe.workoutplanner.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildErrorResponse(String message, HttpStatus status, WebRequest request) {

        return new ErrorResponse(
                message,
                status.value(),
                LocalDateTime.now(),
                getPath(request),
                null
        );
    }

    private ErrorResponse buildValidationErrorResponse(List<String> errors, HttpStatus status, WebRequest request) {

        return new ErrorResponse(
                "Erro de validação",
                status.value(),
                LocalDateTime.now(),
                getPath(request),
                errors
        );
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private ResponseEntity<ErrorResponse> buildResponse(Exception ex, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).body(buildErrorResponse(ex.getMessage(), status, request));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(ExerciseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExerciseNotFoundException(ExerciseNotFoundException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NoExercisesFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoExercisesFoundException(NoExercisesFoundException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(WorkoutNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWorkoutNotFoundException(WorkoutNotFoundException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NoWorkoutsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoWorkoutsFoundException(NoWorkoutsFoundException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ExerciseAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleExerciseAlreadyExistsException(ExerciseAlreadyExistsException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildValidationErrorResponse(errors, HttpStatus.BAD_REQUEST, request));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException ex, WebRequest request) {

        String fieldName = ex.getName();
        String providedType = ex.getValue().toString();
        String requiredType = ex.getRequiredType().getSimpleName();
        String message = String.format("O parâmetro '%s' recebeu o valor '%s', mas deve ser do tipo '%s'", fieldName, providedType, requiredType);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(message, HttpStatus.BAD_REQUEST, request));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}

package com.students.grades_hexagonal.domain.exception;

public class GradeLimitExceededException extends RuntimeException{

    public GradeLimitExceededException(String message){
        super(message);
    }
}

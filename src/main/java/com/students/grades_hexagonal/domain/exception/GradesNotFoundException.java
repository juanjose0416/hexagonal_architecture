package com.students.grades_hexagonal.domain.exception;

public class GradesNotFoundException extends RuntimeException{

    public GradesNotFoundException(String message){
        super(message);
    }
}

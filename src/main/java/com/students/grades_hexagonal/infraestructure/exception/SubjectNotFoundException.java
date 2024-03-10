package com.students.grades_hexagonal.infraestructure.exception;

public class SubjectNotFoundException extends RuntimeException{

    public SubjectNotFoundException(String message){
        super(message);
    }
}

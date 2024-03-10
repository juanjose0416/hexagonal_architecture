package com.students.grades_hexagonal.infraestructure.in.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.students.grades_hexagonal.domain.exception.GradeLimitExceededException;
import com.students.grades_hexagonal.infraestructure.exception.StudentNotFoundException;
import com.students.grades_hexagonal.infraestructure.exception.SubjectNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    protected ResponseEntity<Object> handleStudentError(StudentNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    protected ResponseEntity<Object> handleSubjectError(SubjectNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
    }

    @ExceptionHandler(GradeLimitExceededException.class)
    protected ResponseEntity<Object> handleGradeError(GradeLimitExceededException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}

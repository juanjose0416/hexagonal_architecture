package com.students.grades_hexagonal.app.usecase;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.CreateGradeStudentResponse;

public interface GradeHandlerUseCase {
    void create(CreateGradeRequest createGradeRequest);
}

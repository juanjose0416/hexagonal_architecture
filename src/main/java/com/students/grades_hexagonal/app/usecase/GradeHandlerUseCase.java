package com.students.grades_hexagonal.app.usecase;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;

public interface GradeHandlerUseCase {

    void create(CreateGradeRequest createGradeRequest, String id, Long subjectId);

}
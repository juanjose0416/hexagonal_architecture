package com.students.grades_hexagonal.app.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;
import com.students.grades_hexagonal.domain.model.Student;

public interface GradeHandlerUseCase {

    void create(CreateGradeRequest createGradeRequest, String id, Long subjectId);

    GradeStudentResponse getAllGrades(String studentId) throws JsonProcessingException;

    void updateGrade(String studentId, Long subjectId, CreateGradeRequest request);

}

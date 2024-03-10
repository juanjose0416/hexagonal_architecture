package com.students.grades_hexagonal.app.usecase;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.AverageGradesDto;
import com.students.grades_hexagonal.app.dto.response.GradeResponseDto;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;

public interface GradeHandlerUseCase {

    void create(CreateGradeRequest createGradeRequest, String id, Long subjectId);

    GradeStudentResponse getAllGrades(String studentId) throws JsonProcessingException;

    void updateGrade(String studentId, Long subjectId, CreateGradeRequest request);

    GradeResponseDto getAverage(String studentId, Long subjectId);

    List<AverageGradesDto> getAllAverage(Long subjectId);

}

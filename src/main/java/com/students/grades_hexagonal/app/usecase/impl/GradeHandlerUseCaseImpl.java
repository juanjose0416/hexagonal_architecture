package com.students.grades_hexagonal.app.usecase.impl;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.CreateGradeStudentResponse;
import com.students.grades_hexagonal.app.mapper.CreateGradeRequestMapper;
import com.students.grades_hexagonal.app.mapper.CreateGradeStudentResponseMapper;
import com.students.grades_hexagonal.app.usecase.GradeHandlerUseCase;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.service.GradeService;
import com.students.grades_hexagonal.domain.service.StudentService;
import com.students.grades_hexagonal.domain.service.SubjectService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GradeHandlerUseCaseImpl implements GradeHandlerUseCase {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final CreateGradeRequestMapper createGradeRequestMapper;
    private final CreateGradeStudentResponseMapper createGradeStudentResponseMapper;

    @Override
    public void create(CreateGradeRequest createGradeRequest) {
        Grade grade = gradeService.saveGrades(createGradeRequestMapper.toGrade(createGradeRequest));
        Subject subject = subjectService.saveSubject(createGradeRequestMapper.toSubject(createGradeRequest));
        Student student = createGradeRequestMapper.toStudent(createGradeRequest);
        subject.setGrades(Collections.singletonList(grade));
        student.setSubjects(Collections.singletonList(subject));
        studentService.saveStudent(student);
    }

}

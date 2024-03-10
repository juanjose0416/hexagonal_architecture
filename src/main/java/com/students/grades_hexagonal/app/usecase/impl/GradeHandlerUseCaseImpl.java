package com.students.grades_hexagonal.app.usecase.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.mapper.CreateGradeRequestMapper;
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
@Transactional
@ComponentScan(basePackages = {"com.students.grades_hexagonal.app.mapper"})
public class GradeHandlerUseCaseImpl implements GradeHandlerUseCase {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final CreateGradeRequestMapper createGradeRequestMapper;

    public GradeHandlerUseCaseImpl(GradeService gradeService,
                                   StudentService studentService,
                                   SubjectService subjectService,
                                   CreateGradeRequestMapper createGradeRequestMapper) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.createGradeRequestMapper = createGradeRequestMapper;
    }

    @Override
    public void create(CreateGradeRequest createGradeRequest, String studentId, Long subjectId) {
        Student student = studentService.getStudentById(studentId);
        Subject subject = subjectService.getSubjectById(subjectId);
        Grade grade = createGradeRequestMapper.toGrade(createGradeRequest);
        gradeService.saveGrades(student, subject, grade);
    }

}

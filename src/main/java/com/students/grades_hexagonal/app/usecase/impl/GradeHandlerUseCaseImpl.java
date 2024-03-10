package com.students.grades_hexagonal.app.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.AverageGradesDto;
import com.students.grades_hexagonal.app.dto.response.GradeResponseDto;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;
import com.students.grades_hexagonal.app.mapper.CreateGradeRequestMapper;
import com.students.grades_hexagonal.app.mapper.GradeStudentResponseMapper;
import com.students.grades_hexagonal.app.usecase.GradeHandlerUseCase;
import com.students.grades_hexagonal.domain.exception.GradesNotFoundException;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.service.GradeService;
import com.students.grades_hexagonal.domain.service.StudentService;
import com.students.grades_hexagonal.domain.service.SubjectService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@ComponentScan(basePackages = { "com.students.grades_hexagonal.app.mapper" })
public class GradeHandlerUseCaseImpl implements GradeHandlerUseCase {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final CreateGradeRequestMapper createGradeRequestMapper;
    private final GradeStudentResponseMapper gradeStudentResponseMapper;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GradeHandlerUseCaseImpl(GradeService gradeService,
                                   StudentService studentService,
                                   SubjectService subjectService,
                                   CreateGradeRequestMapper createGradeRequestMapper,
                                   GradeStudentResponseMapper gradeStudentResponseMapper) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.createGradeRequestMapper = createGradeRequestMapper;
        this.gradeStudentResponseMapper = gradeStudentResponseMapper;
    }

    @Override
    public void create(CreateGradeRequest createGradeRequest, String studentId, Long subjectId) {
        Student student = studentService.getStudentById(studentId);
        Subject subject = subjectService.getSubjectById(subjectId);
        Grade grade = createGradeRequestMapper.toGrade(createGradeRequest);
        gradeService.saveGrades(student, subject, grade);
    }

    @Override
    public GradeStudentResponse getAllGrades(String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student.getSubjectsGrade() == null || student.getSubjectsGrade().isEmpty()) {
            throw new GradesNotFoundException("Grades not found");
        }
        GradeStudentResponse response = gradeStudentResponseMapper.toGradeStudentResponse(student);
        return response;
    }

    @Override
    public void updateGrade(String studentId, Long subjectId, CreateGradeRequest request) {
        Student student = studentService.getStudentById(studentId);
        Subject subject = subjectService.getSubjectById(subjectId);
        Grade grade = createGradeRequestMapper.toGrade(request);
        gradeService.updateGrades(student, subject, grade);
    }

    @Override
    public GradeResponseDto getAverage(String studentId, Long subjectId) {
        Student student = studentService.getStudentById(studentId);
        GradeResponseDto gradeResponseDto = new GradeResponseDto();
        Double average = gradeService.average(student.getId(), subjectId);
        gradeResponseDto.setMark(average);
        return gradeResponseDto;
    }

    @Override
    public List<AverageGradesDto> getAllAverage(Long subjectId) {
        List<AverageGradesDto> averageGradesDtos = new ArrayList<>();
        List<Student> students = gradeService.getAllAverage(subjectId);
        students.forEach(student -> {
            AverageGradesDto averageGradesDto = new AverageGradesDto();
            averageGradesDto.setStudentId(student.getIdentificationCode());
            averageGradesDto.setNameStudent(student.getName());
            student.getSubjectsGrade().forEach((key, grades) -> {
                averageGradesDto.setSubjectName(key.getName());
                Double aver = grades.stream().mapToDouble(Grade::getMark).average().getAsDouble();
                averageGradesDto.setAverage(aver);
            });
            averageGradesDtos.add(averageGradesDto);
        });
        return averageGradesDtos;
    }

}

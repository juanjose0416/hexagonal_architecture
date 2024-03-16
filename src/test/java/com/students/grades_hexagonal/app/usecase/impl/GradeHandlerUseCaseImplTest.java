package com.students.grades_hexagonal.app.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.app.dto.response.AverageGradesDto;
import com.students.grades_hexagonal.app.dto.response.GradeResponseDto;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;
import com.students.grades_hexagonal.app.mapper.CreateGradeRequestMapper;
import com.students.grades_hexagonal.app.mapper.GradeStudentResponseMapper;
import com.students.grades_hexagonal.domain.exception.GradesNotFoundException;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.service.GradeService;
import com.students.grades_hexagonal.domain.service.StudentService;
import com.students.grades_hexagonal.domain.service.SubjectService;

@ExtendWith(MockitoExtension.class)
class GradeHandlerUseCaseImplTest {

    @Mock
    private GradeService gradeService;

    @Mock
    private StudentService studentService;

    @Mock
    private SubjectService subjectService;

    @Mock
    private CreateGradeRequestMapper createGradeRequestMapper;

    @Mock
    private GradeStudentResponseMapper gradeStudentResponseMapper;

    @InjectMocks
    private GradeHandlerUseCaseImpl gradeHandlerUseCase;

    private static final String STUDENT_ID = "studentId";

    private Student student;
    private Subject subject;
    private Grade grade;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setId(1L);
        student.setName("studentName");
        subject = new Subject();
        subject.setId(1L);
        subject.setName("subjectName");
        grade = new Grade();
        grade.setId(1L);
        grade.setGradingPeriod("1");
        grade.setMark(2.0);
        Map<Subject, List<Grade>> listMap = new HashMap<>();
        listMap.put(subject, Collections.singletonList(grade));
        student.setSubjectsGrade(listMap);
    }

    @Test
    void create() {
        when(studentService.getStudentById(anyString())).thenReturn(student);
        when(subjectService.getSubjectById(anyLong())).thenReturn(subject);
        when(createGradeRequestMapper.toGrade(any())).thenReturn(grade);
        CreateGradeRequest createGradeRequest = new CreateGradeRequest();
        gradeHandlerUseCase.create(createGradeRequest, STUDENT_ID, subject.getId());
        verify(studentService).getStudentById(STUDENT_ID);
        verify(subjectService).getSubjectById(subject.getId());
        verify(createGradeRequestMapper).toGrade(createGradeRequest);
        verify(gradeService).saveGrades(student, subject, grade);
    }

    @Test
    void getAllGrades() {
        when(studentService.getStudentById(anyString())).thenReturn(student);
        GradeStudentResponse gradeStudentResponse = new GradeStudentResponse();
        when(gradeStudentResponseMapper.toGradeStudentResponse(any())).thenReturn(gradeStudentResponse);
        GradeStudentResponse response = gradeHandlerUseCase.getAllGrades(STUDENT_ID);
        verify(studentService).getStudentById(STUDENT_ID);
        verify(gradeStudentResponseMapper).toGradeStudentResponse(student);
        assertEquals(gradeStudentResponse, response);
    }

    @Test
    void shouldFailIfStudentDoesNotHaveGrades() {
        when(studentService.getStudentById(anyString())).thenReturn(student);
        student.setSubjectsGrade(null);
        GradesNotFoundException ex = assertThrows(GradesNotFoundException.class, () -> {
            gradeHandlerUseCase.getAllGrades(STUDENT_ID);
        });
        verify(studentService).getStudentById(STUDENT_ID);
        assertEquals("Grades not found", ex.getMessage());
    }

    @Test
    void updateGrade() {
        CreateGradeRequest createGradeRequest = new CreateGradeRequest();
        when(studentService.getStudentById(anyString())).thenReturn(student);
        when(subjectService.getSubjectById(anyLong())).thenReturn(subject);
        when(createGradeRequestMapper.toGrade(any())).thenReturn(grade);
        gradeHandlerUseCase.updateGrade(STUDENT_ID, subject.getId(), createGradeRequest);
        verify(studentService).getStudentById(STUDENT_ID);
        verify(subjectService).getSubjectById(subject.getId());
        verify(createGradeRequestMapper).toGrade(createGradeRequest);
        verify(gradeService).updateGrades(student, subject, grade);
    }

    @Test
    void getAverage() {
        GradeResponseDto expected = new GradeResponseDto();
        expected.setMark(2.0);
        when(studentService.getStudentById(anyString())).thenReturn(student);
        when(gradeService.average(anyLong(), anyLong())).thenReturn(2.0);
        GradeResponseDto responseDto = gradeHandlerUseCase.getAverage(STUDENT_ID, subject.getId());
        verify(studentService).getStudentById(STUDENT_ID);
        verify(gradeService).average(student.getId(), subject.getId());
        assertEquals(expected, responseDto);
    }

    @Test
    void getAllAverage() {
        AverageGradesDto expected = new AverageGradesDto();
        expected.setStudentId(student.getIdentificationCode());
        expected.setNameStudent(student.getName());
        expected.setSubjectName(subject.getName());
        expected.setAverage(grade.getMark());
        when(gradeService.getAllAverage(anyLong())).thenReturn(Collections.singletonList(student));
        List<AverageGradesDto> current = gradeHandlerUseCase.getAllAverage(subject.getId());
        verify(gradeService).getAllAverage(subject.getId());
        assertEquals(Collections.singletonList(expected), current);
    }

}
package com.students.grades_hexagonal.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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

import com.students.grades_hexagonal.domain.exception.GradeLimitExceededException;
import com.students.grades_hexagonal.domain.exception.GradesNotFoundException;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.spi.GradePersistencePort;

@ExtendWith(MockitoExtension.class)
class GradeServiceImplTest {

    @Mock
    private GradePersistencePort gradePersistencePort;

    @InjectMocks
    private GradeServiceImpl gradeService;

    private Student student;
    private Subject subject;
    private Grade grade;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setId(1L);
        subject = new Subject();
        subject.setId(1L);
        grade = new Grade();
        grade.setId(1L);
        grade.setGradingPeriod("1");
        grade.setMark(2.0);
        Map<Subject, List<Grade>> listMap = new HashMap<>();
        listMap.put(subject, Collections.singletonList(grade));
        student.setSubjectsGrade(listMap);
    }

    @Test
    void saveGrades() {
        List<Grade> gradeList = Collections.singletonList(grade);
        when(gradePersistencePort.getGradesByStudentIdAndSubjectId(anyLong(), anyLong())).thenReturn(gradeList);
        gradeService.saveGrades(student, subject, grade);
        verify(gradePersistencePort).getGradesByStudentIdAndSubjectId(student.getId(), subject.getId());
        verify(gradePersistencePort).saveGrades(student, subject, grade);
    }

    @Test
    void shouldFailIfStudentHasThreeGrades() {
        List<Grade> gradeList = Arrays.asList(grade, grade, grade);
        when(gradePersistencePort.getGradesByStudentIdAndSubjectId(anyLong(), anyLong())).thenReturn(gradeList);
        GradeLimitExceededException ex = assertThrows(GradeLimitExceededException.class, () -> {
            gradeService.saveGrades(student, subject, grade);
        });
        assertEquals("Number of maximum grades achieved in the subject.", ex.getMessage());
    }

    @Test
    void updateGrades() {
        gradeService.updateGrades(student, subject, grade);
        verify(gradePersistencePort).updateGrades(student, subject, grade);
    }

    @Test
    void average() {
        List<Grade> gradeList = Arrays.asList(grade, grade, grade);
        when(gradePersistencePort.getGradesByStudentIdAndSubjectId(anyLong(), anyLong())).thenReturn(gradeList);
        Double currentResponse = gradeService.average(student.getId(), subject.getId());
        verify(gradePersistencePort).getGradesByStudentIdAndSubjectId(student.getId(), subject.getId());
        assertEquals(2.0, currentResponse);
    }

    @Test
    void shouldFailCalculateAverageIfStudentDoesNotHaveAtLeastThreeGrades() {
        List<Grade> gradeList = Collections.singletonList(grade);
        when(gradePersistencePort.getGradesByStudentIdAndSubjectId(anyLong(), anyLong())).thenReturn(gradeList);
        GradesNotFoundException ex = assertThrows(GradesNotFoundException.class, () -> {
            gradeService.average(student.getId(), subject.getId());
        });
        assertEquals("The student must be three grades", ex.getMessage());
    }

    @Test
    void getAllAverage() {
        List<Student> students = Collections.singletonList(student);
        when(gradePersistencePort.getAllGradesBySubjectId(anyLong())).thenReturn(students);
        List<Student> currentResponse = gradeService.getAllAverage(subject.getId());
        verify(gradePersistencePort).getAllGradesBySubjectId(subject.getId());
        assertEquals(students, currentResponse);
    }

}
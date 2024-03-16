package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.infraestructure.exception.SubjectNotFoundException;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.StudentEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.StudentEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentPersistenceAdapterTest {

    @Mock
    private StudentEntityMapper studentEntityMapper;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentPersistenceAdapter studentPersistenceAdapter;

    @Test
    void shouldGetStudentById() {
        Student student = new Student();
        student.setId(1L);
        student.setIdentificationCode("idCode");
        Subject subject = new Subject();
        subject.setId(1L);
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setGradingPeriod("1");
        grade.setMark(2.0);
        Map<Subject, List<Grade>> listMap = new HashMap<>();
        listMap.put(subject, Collections.singletonList(grade));
        student.setSubjectsGrade(listMap);
        GradesEntity gradesEntity = new GradesEntity();
        gradesEntity.setId(1L);
        StudentEntity studentEntity = new StudentEntity();
        student.setId(1L);
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(1L);
        gradesEntity.setStudent(studentEntity);
        gradesEntity.setSubject(subjectEntity);
        studentEntity.setGradesEntities(Collections.singletonList(gradesEntity));
        when(studentRepository.findByIdentificationCode(anyString())).thenReturn(Optional.of(studentEntity));
        when(studentEntityMapper.toStudent(any())).thenReturn(student);
        when(studentEntityMapper.toSubjectGrade(any())).thenReturn(listMap);
        Student current = studentPersistenceAdapter.getStudentById(student.getIdentificationCode());
        verify(studentRepository).findByIdentificationCode(student.getIdentificationCode());
        verify(studentEntityMapper).toStudent(studentEntity);
        verify(studentEntityMapper).toSubjectGrade(groupGradesBySubject(Collections.singletonList(gradesEntity)));
        assertEquals(student, current);

    }

    @Test
    void shouldFailIfStudentDoesNotExist() {
        when(studentRepository.findByIdentificationCode(anyString())).thenReturn(Optional.empty());
        SubjectNotFoundException ex = assertThrows(SubjectNotFoundException.class, () -> {
            studentPersistenceAdapter.getStudentById("any");
        });
        assertEquals("Student not found", ex.getMessage());
    }

    private Map<SubjectEntity, List<GradesEntity>> groupGradesBySubject(List<GradesEntity> gradesEntities) {
        return gradesEntities.stream().collect(Collectors.groupingBy(GradesEntity::getSubject));
    }

}
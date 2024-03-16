package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

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
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.students.grades_hexagonal.domain.exception.GradesNotFoundException;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.StudentEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.GradesEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.StudentEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.SubjectEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.GradesRepository;

@ExtendWith(MockitoExtension.class)
class GradePersistenceAdapterTest {

    @Mock
    private GradesEntityMapper gradesEntityMapper;

    @Mock
    private StudentEntityMapper studentEntityMapper;

    @Mock
    private SubjectEntityMapper subjectEntityMapper;

    @Mock
    private GradesRepository gradesRepository;

    @InjectMocks
    private GradePersistenceAdapter gradePersistenceAdapter;

    private Student student;
    private Subject subject;
    private Grade grade;
    private GradesEntity gradesEntity;
    private StudentEntity studentEntity;
    private SubjectEntity subjectEntity;
    private Map<Subject, List<Grade>> listMap;

    @BeforeEach
    public void setup() {
        student = new Student();
        student.setId(1L);
        subject = new Subject();
        subject.setId(1L);
        grade = new Grade();
        grade.setId(1L);
        grade.setGradingPeriod("1");
        grade.setMark(2.0);
        listMap = new HashMap<>();
        listMap.put(subject, Collections.singletonList(grade));
        student.setSubjectsGrade(listMap);
        gradesEntity = new GradesEntity();
        gradesEntity.setId(1L);
        studentEntity = new StudentEntity();
        student.setId(1L);
        subjectEntity = new SubjectEntity();
        subjectEntity.setId(1L);
        gradesEntity.setStudent(studentEntity);
        gradesEntity.setSubject(subjectEntity);
        studentEntity.setGradesEntities(Collections.singletonList(gradesEntity));
    }

    @Test
    void saveGrades() {
        when(gradesEntityMapper.toGradesEntity(any())).thenReturn(gradesEntity);
        when(studentEntityMapper.toStudentEntity(any())).thenReturn(studentEntity);
        when(subjectEntityMapper.toSubjectEntity(any())).thenReturn(subjectEntity);
        gradesEntity.setStudent(studentEntity);
        gradesEntity.setSubject(subjectEntity);
        gradePersistenceAdapter.saveGrades(student, subject, grade);
        verify(gradesEntityMapper).toGradesEntity(grade);
        verify(studentEntityMapper).toStudentEntity(student);
        verify(subjectEntityMapper).toSubjectEntity(subject);
        verify(gradesRepository).save(gradesEntity);
    }

    @Test
    void getGradesByStudentIdAndSubjectId() {
        Optional<List<GradesEntity>> gradeList = Optional.of(Collections.singletonList(gradesEntity));
        when(gradesRepository.findByStudentIdAndSubjectId(anyLong(), anyLong())).thenReturn(gradeList);
        when(gradesEntityMapper.toGrade(any())).thenReturn(grade);
        List<Grade> currentGrade = gradePersistenceAdapter.getGradesByStudentIdAndSubjectId(1L, 1L);
        verify(gradesRepository).findByStudentIdAndSubjectId(1L, 1L);
        verify(gradesEntityMapper).toGrade(gradesEntity);
        assertEquals(Collections.singletonList(grade), currentGrade);
    }

    @Test
    void updateGrades() {
        when(gradesRepository.findByStudentIdAndSubjectIdAndGradingPeriod(anyLong(), anyLong(), anyString()))
                .thenReturn(Optional.of(gradesEntity));
        gradePersistenceAdapter.updateGrades(student, subject, grade);
        gradesEntity.setMark(grade.getMark());
        verify(gradesRepository).findByStudentIdAndSubjectIdAndGradingPeriod(student.getId(), subject.getId(),
                grade.getGradingPeriod());
        verify(gradesRepository).save(gradesEntity);

    }

    @Test
    void getAllGradesBySubjectId() {
        when(gradesRepository.findBySubjectId(anyLong())).thenReturn(Collections.singletonList(gradesEntity));
        when(studentEntityMapper.toStudent(any())).thenReturn(student);
        when(studentEntityMapper.toSubjectGrade(any())).thenReturn(listMap);
        List<Student> current = gradePersistenceAdapter.getAllGradesBySubjectId(subject.getId());
        student.setSubjectsGrade(listMap);
        verify(gradesRepository).findBySubjectId(subject.getId());
        verify(studentEntityMapper).toStudent(studentEntity);
        verify(studentEntityMapper).toSubjectGrade(groupGradesBySubject(studentEntity.getGradesEntities()));
        assertEquals(Collections.singletonList(student), current);

    }

    @Test
    void shouldFailIfGradeDoesNotExist() {
        when(gradesRepository.findBySubjectId(anyLong())).thenReturn(Collections.emptyList());
        GradesNotFoundException ex = assertThrows(GradesNotFoundException.class, () -> {
            gradePersistenceAdapter.getAllGradesBySubjectId(subject.getId());
        });
        assertEquals("There aren't grades or students in the subject", ex.getMessage());

    }

    private Map<SubjectEntity, List<GradesEntity>> groupGradesBySubject(List<GradesEntity> gradesEntities) {
        return gradesEntities.stream().collect(Collectors.groupingBy(GradesEntity::getSubject));
    }

}
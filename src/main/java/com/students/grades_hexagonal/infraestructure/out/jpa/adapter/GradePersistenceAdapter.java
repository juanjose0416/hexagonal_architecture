package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.spi.GradePersistencePort;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.GradesEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.StudentEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.SubjectEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.GradesRepository;

@Component
public class GradePersistenceAdapter implements GradePersistencePort {

    private final GradesEntityMapper gradesEntityMapper;
    private final StudentEntityMapper studentEntityMapper;
    private final SubjectEntityMapper subjectEntityMapper;
    private final GradesRepository gradesRepository;

    public GradePersistenceAdapter(GradesEntityMapper gradesEntityMapper,
                                   StudentEntityMapper studentEntityMapper,
                                   SubjectEntityMapper subjectEntityMapper,
                                   GradesRepository gradesRepository) {
        this.gradesEntityMapper = gradesEntityMapper;
        this.studentEntityMapper = studentEntityMapper;
        this.subjectEntityMapper = subjectEntityMapper;
        this.gradesRepository = gradesRepository;
    }

    @Override
    public void saveGrades(Student student, Subject subject, Grade grade) {
        GradesEntity gradesEntity = gradesEntityMapper.toGradesEntity(grade);
        gradesEntity.setStudent(studentEntityMapper.toStudentEntity(student));
        gradesEntity.setSubject(subjectEntityMapper.toSubjectEntity(subject));
        gradesRepository.save(gradesEntity);
    }

    @Override
    public List<Grade> getGradesByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        List<GradesEntity> gradesEntities = gradesRepository.findByStudentIdAndSubjectId(studentId, subjectId)
                .orElse(Collections.emptyList());
        return gradesEntities.stream().map(gradesEntityMapper::toGrade).collect(Collectors.toList());
    }

}

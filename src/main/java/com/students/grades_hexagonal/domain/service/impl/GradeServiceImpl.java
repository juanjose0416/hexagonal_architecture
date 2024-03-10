package com.students.grades_hexagonal.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.service.GradeService;
import com.students.grades_hexagonal.domain.spi.GradePersistencePort;
import com.students.grades_hexagonal.domain.exception.GradeLimitExceededException;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradePersistencePort gradePersistencePort;

    private static final int MAX_GRADES = 3;

    public GradeServiceImpl(GradePersistencePort gradePersistencePort) {
        this.gradePersistencePort = gradePersistencePort;
    }

    @Override
    public void saveGrades(Student student, Subject subject, Grade grade) {
        List<Grade> gradeList = gradePersistencePort.getGradesByStudentIdAndSubjectId(student.getId(), subject.getId());
        if (gradeList.size() >= MAX_GRADES) {
            throw new GradeLimitExceededException("Number of maximum grades achieved in the subject.");
        }
        gradePersistencePort.saveGrades(student, subject, grade);
    }

}

package com.students.grades_hexagonal.domain.spi;

import java.util.List;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;

public interface GradePersistencePort {

    void saveGrades(Student student, Subject subject, Grade grade);

    List<Grade> getGradesByStudentIdAndSubjectId(Long studentId, Long subjectId);

    void updateGrades(Student student, Subject subject, Grade grade);

}

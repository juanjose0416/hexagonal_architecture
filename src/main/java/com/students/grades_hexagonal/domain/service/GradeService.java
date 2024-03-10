package com.students.grades_hexagonal.domain.service;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;

public interface GradeService {

    void saveGrades(Student student, Subject subject, Grade grade);

}

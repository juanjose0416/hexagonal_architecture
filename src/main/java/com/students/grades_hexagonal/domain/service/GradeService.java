package com.students.grades_hexagonal.domain.service;

import com.students.grades_hexagonal.domain.model.Grade;

public interface GradeService {

    Grade saveGrades(Grade grade);
}

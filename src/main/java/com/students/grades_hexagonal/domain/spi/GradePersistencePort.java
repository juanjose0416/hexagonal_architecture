package com.students.grades_hexagonal.domain.spi;

import com.students.grades_hexagonal.domain.model.Grade;

public interface GradePersistencePort {

    Grade saveGrades(Grade grade);
}

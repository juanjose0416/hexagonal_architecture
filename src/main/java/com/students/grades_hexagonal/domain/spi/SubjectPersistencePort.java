package com.students.grades_hexagonal.domain.spi;

import com.students.grades_hexagonal.domain.model.Subject;

public interface SubjectPersistencePort {

    Subject getSubjectById(Long subjectId);
}

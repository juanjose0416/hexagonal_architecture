package com.students.grades_hexagonal.domain.service;

import com.students.grades_hexagonal.domain.model.Subject;

public interface SubjectService {

    Subject getSubjectById(Long subjectId);

}

package com.students.grades_hexagonal.domain.service.impl;

import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.service.SubjectService;
import com.students.grades_hexagonal.domain.spi.SubjectPersistencePort;

@Service
public class SubjectServiceImpl implements SubjectService{

    private final SubjectPersistencePort subjectPersistencePort;

    public SubjectServiceImpl(SubjectPersistencePort subjectPersistencePort) {
        this.subjectPersistencePort = subjectPersistencePort;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectPersistencePort.saveSubject(subject);
    }

}

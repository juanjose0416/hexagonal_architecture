package com.students.grades_hexagonal.domain.service.impl;

import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.service.GradeService;
import com.students.grades_hexagonal.domain.spi.GradePersistencePort;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradePersistencePort gradePersistencePort;

    public GradeServiceImpl(GradePersistencePort gradePersistencePort) {
        this.gradePersistencePort = gradePersistencePort;
    }

    @Override
    public Grade saveGrades(Grade grade) {
        return gradePersistencePort.saveGrades(grade);
    }

}

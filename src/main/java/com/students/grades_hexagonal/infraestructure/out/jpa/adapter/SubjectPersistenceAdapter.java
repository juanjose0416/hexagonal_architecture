package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.spi.SubjectPersistencePort;
import com.students.grades_hexagonal.infraestructure.exception.SubjectNotFoundException;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.SubjectEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.SubjectRepository;

@Component
public class SubjectPersistenceAdapter implements SubjectPersistencePort {

    private final SubjectEntityMapper subjectEntityMapper;
    private final SubjectRepository subjectRepository;

    public SubjectPersistenceAdapter(SubjectEntityMapper subjectEntityMapper,
                                     SubjectRepository subjectRepository) {
        this.subjectEntityMapper = subjectEntityMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found"));
        return subjectEntityMapper.toSubject(subjectEntity);
    }

}

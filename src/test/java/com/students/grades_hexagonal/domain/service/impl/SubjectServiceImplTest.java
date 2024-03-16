package com.students.grades_hexagonal.domain.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.domain.spi.SubjectPersistencePort;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Mock
    private SubjectPersistencePort subjectPersistencePort;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Test
    void getSubjectById() {
        Subject subject = new Subject();
        when(subjectPersistencePort.getSubjectById(anyLong())).thenReturn(subject);
        Subject current = subjectService.getSubjectById(1L);
        verify(subjectPersistencePort).getSubjectById(1L);
        assertEquals(subject, current);
    }

}
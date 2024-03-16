package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.infraestructure.exception.SubjectNotFoundException;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.SubjectEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.SubjectRepository;

@ExtendWith(MockitoExtension.class)
class SubjectPersistenceAdapterTest {

    @Mock
    private SubjectEntityMapper subjectEntityMapper;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectPersistenceAdapter subjectPersistenceAdapter;

    @Test
    void getSubjectById() {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(1L);
        Subject subject = new Subject();
        subject.setId(1L);
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subjectEntity));
        when(subjectEntityMapper.toSubject(any())).thenReturn(subject);
        Subject currentSubject = subjectPersistenceAdapter.getSubjectById(subject.getId());
        verify(subjectRepository).findById(subject.getId());
        verify(subjectEntityMapper).toSubject(subjectEntity);
        assertEquals(subject, currentSubject);
    }

    @Test
    void shouldFailIfSubjectDoesNotExist() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());
        SubjectNotFoundException ex = assertThrows(SubjectNotFoundException.class, () -> {
            subjectPersistenceAdapter.getSubjectById(1L);
        });
        assertEquals("Subject not found", ex.getMessage());
    }

}
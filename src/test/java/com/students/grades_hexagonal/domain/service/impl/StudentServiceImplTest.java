package com.students.grades_hexagonal.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.spi.StudentPersistencePort;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentPersistencePort studentPersistencePort;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getStudentById() {
        Student student = new Student();
        when(studentPersistencePort.getStudentById(anyString())).thenReturn(student);
        Student current = studentService.getStudentById("studentId");
        verify(studentPersistencePort).getStudentById("studentId");
        assertEquals(student, current);
    }

}
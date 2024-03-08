package com.students.grades_hexagonal.domain.service.impl;

import org.springframework.stereotype.Service;

import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.service.StudentService;
import com.students.grades_hexagonal.domain.spi.StudentPersistencePort;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentPersistencePort studentPersistencePort;

    public StudentServiceImpl(StudentPersistencePort studentPersistencePort) {
        this.studentPersistencePort = studentPersistencePort;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentPersistencePort.saveStudent(student);
    }

}

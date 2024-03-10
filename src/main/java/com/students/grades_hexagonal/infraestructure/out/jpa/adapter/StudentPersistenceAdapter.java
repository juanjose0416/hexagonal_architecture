package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.spi.StudentPersistencePort;
import com.students.grades_hexagonal.infraestructure.exception.SubjectNotFoundException;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.StudentEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.StudentEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
public class StudentPersistenceAdapter implements StudentPersistencePort {

    private final StudentEntityMapper studentEntityMapper;
    private final StudentRepository studentRepository;

    public StudentPersistenceAdapter(StudentEntityMapper studentEntityMapper,
                                     StudentRepository studentRepository) {
        this.studentEntityMapper = studentEntityMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentById(String studentId) {
        StudentEntity studentEntity = studentRepository.findByIdentificationCode(studentId)
                .orElseThrow(() -> new SubjectNotFoundException("Student not found"));
        return studentEntityMapper.toStudent(studentEntity);
    }

}

package com.students.grades_hexagonal.infraestructure.out.jpa.adapter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.spi.StudentPersistencePort;
import com.students.grades_hexagonal.infraestructure.exception.SubjectNotFoundException;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.StudentEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.StudentEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.StudentRepository;

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
        Student student = studentEntityMapper.toStudent(studentEntity);
        student.setSubjectsGrade(
                studentEntityMapper.toSubjectGrade(groupGradesBySubject(studentEntity.getGradesEntities())));
        return student;
    }

    private Map<SubjectEntity, List<GradesEntity>> groupGradesBySubject(List<GradesEntity> gradesEntities) {
        return gradesEntities.stream().collect(Collectors.groupingBy(GradesEntity::getSubject));
    }

}

package com.students.grades_hexagonal.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.students.grades_hexagonal.domain.service.GradeService;
import com.students.grades_hexagonal.domain.service.StudentService;
import com.students.grades_hexagonal.domain.service.SubjectService;
import com.students.grades_hexagonal.domain.service.impl.GradeServiceImpl;
import com.students.grades_hexagonal.domain.service.impl.StudentServiceImpl;
import com.students.grades_hexagonal.domain.service.impl.SubjectServiceImpl;
import com.students.grades_hexagonal.domain.spi.GradePersistencePort;
import com.students.grades_hexagonal.domain.spi.StudentPersistencePort;
import com.students.grades_hexagonal.domain.spi.SubjectPersistencePort;
import com.students.grades_hexagonal.infraestructure.out.jpa.adapter.GradePersistenceAdapter;
import com.students.grades_hexagonal.infraestructure.out.jpa.adapter.StudentPersistenceAdapter;
import com.students.grades_hexagonal.infraestructure.out.jpa.adapter.SubjectPersistenceAdapter;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.GradesEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.StudentEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.mapper.SubjectEntityMapper;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.GradesRepository;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.StudentRepository;
import com.students.grades_hexagonal.infraestructure.out.jpa.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@ComponentScan(basePackages = {"com.students.grades_hexagonal.infraestructure.out.jpa.mapper"})
public class BeanConfiguration {

    private final GradesRepository gradesRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradesEntityMapper gradesEntityMapper;
    private final SubjectEntityMapper subjectEntityMapper;
    private final StudentEntityMapper studentEntityMapper;

    public BeanConfiguration(GradesRepository gradesRepository,
                             StudentRepository studentRepository,
                             SubjectRepository subjectRepository,
                             GradesEntityMapper gradesEntityMapper,
                             SubjectEntityMapper subjectEntityMapper,
                             StudentEntityMapper studentEntityMapper) {
        this.gradesRepository = gradesRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.gradesEntityMapper = gradesEntityMapper;
        this.subjectEntityMapper = subjectEntityMapper;
        this.studentEntityMapper = studentEntityMapper;
    }

    @Bean
    public GradePersistencePort gradePersistencePort() {
        return new GradePersistenceAdapter(gradesEntityMapper, studentEntityMapper, subjectEntityMapper,
                gradesRepository);
    }

    @Bean
    public GradeService gradeService() {
        return new GradeServiceImpl(gradePersistencePort());
    }

    @Bean
    public SubjectPersistencePort subjectPersistencePort() {
        return new SubjectPersistenceAdapter(subjectEntityMapper, subjectRepository);
    }

    @Bean
    public SubjectService subjectService() {
        return new SubjectServiceImpl(subjectPersistencePort());
    }

    @Bean
    public StudentPersistencePort studentPersistencePort() {
        return new StudentPersistenceAdapter(studentEntityMapper, studentRepository);
    }

    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl(studentPersistencePort());
    }

}

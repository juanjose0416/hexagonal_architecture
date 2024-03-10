package com.students.grades_hexagonal.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.StudentEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StudentEntityMapper {

    @Mapping(source = "studentEntity.id", target = "id")
    @Mapping(source = "studentEntity.identificationCode", target = "identificationCode")
    @Mapping(source = "studentEntity.name", target = "name")
    Student toStudent(StudentEntity studentEntity);

    @Mapping(source = "student.id", target = "id")
    @Mapping(source = "student.identificationCode", target = "identificationCode")
    @Mapping(source = "student.name", target = "name")
    StudentEntity toStudentEntity(Student student);

}

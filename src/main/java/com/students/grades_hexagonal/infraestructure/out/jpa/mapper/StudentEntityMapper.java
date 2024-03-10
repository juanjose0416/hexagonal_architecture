package com.students.grades_hexagonal.infraestructure.out.jpa.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.StudentEntity;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StudentEntityMapper {

    @Mapping(source = "studentEntity.id", target = "id")
    @Mapping(source = "studentEntity.identificationCode", target = "identificationCode")
    @Mapping(source = "studentEntity.name", target = "name")
    Student toStudent(StudentEntity studentEntity);

    Map<Subject, List<Grade>> toSubjectGrade(Map<SubjectEntity, List<GradesEntity>> subjectGrades);

    default List<Grade> map(List<GradesEntity> value) {
        return value.stream()
                .map(this::toGrade)
                .collect(Collectors.toList());
    }

    default Subject toSubjectDto(SubjectEntity subjectEntity) {
        Subject subject = new Subject();
        subject.setId(subjectEntity.getId());
        subject.setName(subjectEntity.getName());
        return subject;
    }

    Grade toGrade(GradesEntity gradesEntity);

    @Mapping(source = "student.id", target = "id")
    @Mapping(source = "student.identificationCode", target = "identificationCode")
    @Mapping(source = "student.name", target = "name")
    StudentEntity toStudentEntity(Student student);

}

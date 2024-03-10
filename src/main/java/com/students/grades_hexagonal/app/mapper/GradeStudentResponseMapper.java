package com.students.grades_hexagonal.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.app.dto.response.GradeResponseDto;
import com.students.grades_hexagonal.app.dto.response.GradeStudentResponse;
import com.students.grades_hexagonal.app.dto.response.SubjectDto;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface GradeStudentResponseMapper {

    @Mapping(source = "student.identificationCode", target = "identificationCode")
    @Mapping(source = "student.name", target = "name")
    @Mapping(source = "student.subjectsGrade", target = "subjectsGrade")
    GradeStudentResponse toGradeStudentResponse(Student student);

    default List<GradeResponseDto> map(List<Grade> value) {
        return value.stream()
                .map(this::toGrade)
                .collect(Collectors.toList());
    }

    default SubjectDto toSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        return subjectDto;
    }

    GradeResponseDto toGrade(Grade grade);

}

package com.students.grades_hexagonal.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.students.grades_hexagonal.app.dto.response.CreateGradeStudentResponse;
import com.students.grades_hexagonal.app.dto.response.GradeResponseDto;
import com.students.grades_hexagonal.app.dto.response.SubjectDto;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CreateGradeStudentResponseMapper {

    @Mapping(source = "student.identificationCode", target = "identificationCode")
    @Mapping(source = "student.name", target = "name")
    @Mapping(source = "student.subjects", target = "subjectDtoList")
    CreateGradeStudentResponse toStudentResponse(Student student);

    @Mapping(source = "grade.mark", target = "mark")
    @Mapping(source = "grade.gradingPeriod", target = "gradingPeriod")
    GradeResponseDto toGradeResponse(Grade grade);

    @Mapping(source = "subject.name", target = "name")
    @Mapping(source = "subject.grades", target = "gradeResponseDtoList")
    SubjectDto toSubjectDto(Subject subject);

}

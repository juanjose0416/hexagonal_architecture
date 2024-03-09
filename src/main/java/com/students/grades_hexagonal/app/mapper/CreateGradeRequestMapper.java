package com.students.grades_hexagonal.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CreateGradeRequestMapper {

    @Mapping(source = "createGradeRequest.studentId", target = "identificationCode")
    Student toStudent(CreateGradeRequest createGradeRequest);

    @Mapping(source = "createGradeRequest.subjectId", target = "id")
    Subject toSubject(CreateGradeRequest createGradeRequest);

    @Mapping(source = "createGradeRequest.mark", target = "mark")
    @Mapping(source = "createGradeRequest.gradingPeriod", target = "gradingPeriod")
    Grade toGrade(CreateGradeRequest createGradeRequest);
}

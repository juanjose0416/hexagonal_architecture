package com.students.grades_hexagonal.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.students.grades_hexagonal.app.dto.request.CreateGradeRequest;
import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.domain.model.Student;
import com.students.grades_hexagonal.domain.model.Subject;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface CreateGradeRequestMapper {

    @Mapping(source = "studentId", target = "identificationCode")
    Student toStudent(String studentId);

    @Mapping(source = "createGradeRequest.mark", target = "mark")
    @Mapping(source = "createGradeRequest.gradingPeriod", target = "gradingPeriod")
    Grade toGrade(CreateGradeRequest createGradeRequest);
}

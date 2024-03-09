package com.students.grades_hexagonal.app.dto.response;

import java.util.List;

import com.students.grades_hexagonal.domain.model.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeStudentResponse {
    private String identificationCode;
    private String name;
    List<SubjectDto> subjectDtoList;
}

package com.students.grades_hexagonal.app.dto.response;

import com.students.grades_hexagonal.domain.model.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeResponse {

    private Student student;

}

package com.students.grades_hexagonal.app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeRequest {

    private String studentId;
    private Long subjectId;
    private double mark;
    private String gradingPeriod;

}

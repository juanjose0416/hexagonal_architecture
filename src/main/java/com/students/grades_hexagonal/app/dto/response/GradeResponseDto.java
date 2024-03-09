package com.students.grades_hexagonal.app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeResponseDto {
    private double mark;
    private String gradingPeriod;
}

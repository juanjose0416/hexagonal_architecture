package com.students.grades_hexagonal.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeResponseDto {

    @JsonProperty
    private double mark;
    @JsonProperty
    private String gradingPeriod;

    public GradeResponseDto(double mark, String gradingPeriod) {
        this.mark = mark;
        this.gradingPeriod = gradingPeriod;
    }

}

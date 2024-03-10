package com.students.grades_hexagonal.app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GradeResponseDto {

    @JsonProperty
    private double mark;
    @JsonProperty
    private String gradingPeriod;

    public GradeResponseDto(double mark, String gradingPeriod) {
        this.mark = mark;
        this.gradingPeriod = gradingPeriod;
    }

    public GradeResponseDto() {
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getGradingPeriod() {
        return gradingPeriod;
    }

    public void setGradingPeriod(String gradingPeriod) {
        this.gradingPeriod = gradingPeriod;
    }

}

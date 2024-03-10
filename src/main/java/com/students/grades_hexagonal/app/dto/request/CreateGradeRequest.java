package com.students.grades_hexagonal.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateGradeRequest {
    @JsonProperty
    private double mark;
    @JsonProperty
    private String gradingPeriod;

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

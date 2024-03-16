package com.students.grades_hexagonal.app.dto.request;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateGradeRequest that = (CreateGradeRequest) o;
        return Double.compare(that.mark, mark) == 0 &&
                Objects.equals(gradingPeriod, that.gradingPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, gradingPeriod);
    }

}

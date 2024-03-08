package com.students.grades_hexagonal.domain.model;

public class Grade {

    private double mark;
    private String gradingPeriod;

    public Grade(double mark, String gradingPeriod) {
        this.mark = mark;
        this.gradingPeriod = gradingPeriod;
    }

    public Grade() {
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

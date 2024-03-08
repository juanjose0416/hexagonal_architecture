package com.students.grades_hexagonal.domain.model;

public class Grades {

    private double mark;
    private String gradingPeriod;

    public Grades(double mark, String gradingPeriod) {
        this.mark = mark;
        this.gradingPeriod = gradingPeriod;
    }

    public Grades() {
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

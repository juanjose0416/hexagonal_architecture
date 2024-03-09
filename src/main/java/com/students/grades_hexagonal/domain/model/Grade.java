package com.students.grades_hexagonal.domain.model;

public class Grade {

    Long id;
    private double mark;
    private String gradingPeriod;

    public Grade(Long id, double mark, String gradingPeriod) {
        this.id = id;
        this.mark = mark;
        this.gradingPeriod = gradingPeriod;
    }

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

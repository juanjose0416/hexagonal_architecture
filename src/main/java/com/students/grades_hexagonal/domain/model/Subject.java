package com.students.grades_hexagonal.domain.model;

import java.util.List;

public class Subject {

    private Long id;
    private String name;
    private List<Grades> grades;

    public Subject(Long id, String name, List<Grades> grades) {
        this.id = id;
        this.name = name;
        this.grades = grades;
    }

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public double calculateAverage(){
        return grades.stream()
                .mapToDouble(Grades::getMark)
                .average()
                .orElse(0.0);
    }

}

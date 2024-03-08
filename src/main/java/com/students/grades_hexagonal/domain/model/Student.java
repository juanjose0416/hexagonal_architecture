package com.students.grades_hexagonal.domain.model;

import java.util.List;

public class Student {

    private String identificationCode;
    private String name;
    private List<Subject> subjects;

    public Student(String identificationCode,
                   String name,
                   List<Subject> subjects) {
        this.identificationCode = identificationCode;
        this.name = name;
        this.subjects = subjects;
    }

    public Student() {
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public double calculateAverageBySubject(long idSubject) {
        return subjects.stream()
                .filter(subject -> subject.getId() == idSubject)
                .findFirst()
                .map(Subject::calculateAverage)
                .orElse(0.0);
    }

}

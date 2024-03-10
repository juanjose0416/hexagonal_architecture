package com.students.grades_hexagonal.domain.model;

import java.util.List;
import java.util.Map;

public class Student {

    private Long id;
    private String identificationCode;
    private String name;
    private Map<Subject, List<Grade>> subjectsGrade;

    public Student(Long id,
                   String identificationCode,
                   String name,
                   Map<Subject, List<Grade>> subjectsGrade) {
        this.id = id;
        this.identificationCode = identificationCode;
        this.name = name;
        this.subjectsGrade = subjectsGrade;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Map<Subject, List<Grade>> getSubjectsGrade() {
        return subjectsGrade;
    }

    public void setSubjectsGrade(Map<Subject, List<Grade>> subjectsGrade) {
        this.subjectsGrade = subjectsGrade;
    }

}

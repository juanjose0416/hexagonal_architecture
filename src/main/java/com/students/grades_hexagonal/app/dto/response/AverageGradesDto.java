package com.students.grades_hexagonal.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AverageGradesDto {

    @JsonProperty
    String studentId;
    @JsonProperty
    String nameStudent;
    @JsonProperty
    String subjectName;
    @JsonProperty
    Double average;

    public AverageGradesDto(String studentId, String nameStudent, String subjectName, Double average) {
        this.studentId = studentId;
        this.nameStudent = nameStudent;
        this.subjectName = subjectName;
        this.average = average;
    }

    public AverageGradesDto() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

}

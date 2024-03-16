package com.students.grades_hexagonal.app.dto.response;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AverageGradesDto that = (AverageGradesDto) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(nameStudent, that.nameStudent) &&
                Objects.equals(subjectName, that.subjectName) && Objects.equals(average, that.average);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, nameStudent, subjectName, average);
    }

}

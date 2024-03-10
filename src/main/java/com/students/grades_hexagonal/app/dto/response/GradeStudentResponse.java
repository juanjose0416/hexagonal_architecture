package com.students.grades_hexagonal.app.dto.response;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeStudentResponse {

    @JsonProperty
    private String identificationCode;
    @JsonProperty
    private String name;
    @JsonProperty
    private Map<SubjectDto, List<GradeResponseDto>> subjectsGrade;

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

    public Map<SubjectDto, List<GradeResponseDto>> getSubjectsGrade() {
        return subjectsGrade;
    }

    public void setSubjectsGrade(Map<SubjectDto, List<GradeResponseDto>> subjectsGrade) {
        this.subjectsGrade = subjectsGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GradeStudentResponse that = (GradeStudentResponse) o;
        return Objects.equals(identificationCode, that.identificationCode) &&
                Objects.equals(name, that.name) && Objects.equals(subjectsGrade, that.subjectsGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationCode, name, subjectsGrade);
    }

}

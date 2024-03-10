package com.students.grades_hexagonal.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeRequest {
    @JsonProperty
    private double mark;
    @JsonProperty
    private String gradingPeriod;

}

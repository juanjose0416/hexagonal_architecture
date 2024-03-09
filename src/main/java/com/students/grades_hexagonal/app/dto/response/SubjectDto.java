package com.students.grades_hexagonal.app.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {
    private Long id;
    private String name;
    List<GradeResponseDto> gradeResponseDtoList;
}

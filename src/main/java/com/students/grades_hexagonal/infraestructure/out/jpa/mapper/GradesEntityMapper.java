package com.students.grades_hexagonal.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.students.grades_hexagonal.domain.model.Grade;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.GradesEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface GradesEntityMapper {

    GradesEntityMapper INSTANCE = Mappers.getMapper(GradesEntityMapper.class);

    @Mapping(source = "gradesEntity.id", target = "id")
    @Mapping(source = "gradesEntity.mark", target = "mark")
    @Mapping(source = "gradesEntity.gradingPeriod", target = "gradingPeriod")
    Grade toGrade(GradesEntity gradesEntity);

    @Mapping(source = "grade.id", target = "id")
    @Mapping(source = "grade.mark", target = "mark")
    @Mapping(source = "grade.gradingPeriod", target = "gradingPeriod")
    GradesEntity toGradesEntity(Grade grade);

}

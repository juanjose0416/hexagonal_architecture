package com.students.grades_hexagonal.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.students.grades_hexagonal.domain.model.Subject;
import com.students.grades_hexagonal.infraestructure.out.jpa.entity.SubjectEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SubjectEntityMapper {

    SubjectEntityMapper INSTANCE = Mappers.getMapper(SubjectEntityMapper.class);

    @Mapping(source = "subjectEntity.id", target = "id")
    @Mapping(source = "subjectEntity.name", target = "name")
    Subject toSubject(SubjectEntity subjectEntity);

    @Mapping(source = "subject.id", target = "id")
    @Mapping(source = "subject.name", target = "name")
    SubjectEntity toSubjectEntity(Subject subject);

}

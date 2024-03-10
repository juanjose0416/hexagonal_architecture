package com.students.grades_hexagonal.app.mapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.students.grades_hexagonal.app.dto.response.SubjectDto;

public class SubjectDtoSerializer extends JsonSerializer<SubjectDto> {
    @Override
    public void serialize(SubjectDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Implementa la lógica para serializar la clave (SubjectDto) como necesites.
        gen.writeFieldName(value.getId().toString());
    }
}
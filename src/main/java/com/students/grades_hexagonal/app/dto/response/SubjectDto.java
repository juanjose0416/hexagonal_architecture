package com.students.grades_hexagonal.app.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.students.grades_hexagonal.app.mapper.SubjectDtoSerializer;

@JsonSerialize(using = SubjectDtoSerializer.class)
public class SubjectDto implements Serializable{

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;

    public SubjectDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubjectDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

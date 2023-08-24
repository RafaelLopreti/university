package com.lopreti.university.domain.dtos;

public class ParameterValueDto {

    private String name;

    private String value;

    public ParameterValueDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}

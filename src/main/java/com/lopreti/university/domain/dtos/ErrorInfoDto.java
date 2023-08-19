package com.lopreti.university.domain.dtos;

public class ErrorInfoDto {

    private final String code;
    private final String message;

    public ErrorInfoDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

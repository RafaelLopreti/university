package com.lopreti.university.domain.dtos;

public class ErrorInfoDto {

    private final String code;
    private final String message;
    private String action;

    public ErrorInfoDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorInfoDto(String code, String message, String action) {
        this.code = code;
        this.message = message;
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getAction() {
        return action;
    }

}

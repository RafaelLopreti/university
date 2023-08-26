package com.lopreti.university.domain.dtos;

public class ErrorResponseDto {

    private ErrorInfoDto errorInfoDto;

    public ErrorResponseDto(String code, String message) {
        this.errorInfoDto = new ErrorInfoDto(code, message);
    }

    public ErrorResponseDto(String code, String message, String action) {
        this.errorInfoDto = new ErrorInfoDto(code, message, action);
    }

    public ErrorInfoDto getError() {
        return errorInfoDto;
    }

}

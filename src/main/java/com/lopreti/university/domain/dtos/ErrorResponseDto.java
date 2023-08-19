package com.lopreti.university.domain.dtos;

public class ErrorResponseDto {

    private ErrorInfoDto errorInfoDto;

    public ErrorResponseDto(String code, String message) {
        this.errorInfoDto = new ErrorInfoDto(code, message);
    }

    public ErrorInfoDto getError() {
        return errorInfoDto;
    }

}

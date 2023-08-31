package com.lopreti.university.domain.valueObjects;

public enum GlobalErrorCodes {

    ADDRESS_NOT_FOUND_ERROR_CODE("UNICODE-011");

    private final String code;

    GlobalErrorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

package com.lopreti.university.domain.exception;

import static java.lang.String.format;

public class PeriodNotFoundException extends RuntimeException {

    private String period;

    public PeriodNotFoundException(String period) {
        this.period = period;
    }

    public String getCode() {
        return "UNICODE-019";
    }

    public String getMessage() {
        return format("Period %s not found.", period);
    }

}

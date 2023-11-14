package com.lopreti.university.domain.exception;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.PERIOD_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class PeriodNotFoundException extends RuntimeException {

    protected String period;

    public PeriodNotFoundException(String period) {
        this.period = period;
    }

    public String getCode() {
        return PERIOD_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("Period %s not found.", period);
    }

}

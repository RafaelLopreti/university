package com.lopreti.university.domain.exception.course;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.PERIOD_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class CoursePeriodNotFoundException extends RuntimeException {

    protected String period;

    public CoursePeriodNotFoundException(String period) {
        this.period = period;
    }

    public String getCode() {
        return PERIOD_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        return format("Period %s not found.", period);
    }

}

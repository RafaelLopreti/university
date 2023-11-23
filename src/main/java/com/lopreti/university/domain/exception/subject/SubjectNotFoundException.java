package com.lopreti.university.domain.exception.subject;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.SUBJECT_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class SubjectNotFoundException extends RuntimeException {

    protected Long subjectId;

    protected String subjectName;

    public SubjectNotFoundException(Long subjectId) {
        this.subjectId = subjectId;
    }

    public SubjectNotFoundException(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCode() {
        return SUBJECT_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        if (subjectId != null) {
            return format("Subject with id %s not found.", subjectId);
        } else if (subjectName != null) {
            return format("Subject with name %s not found.", subjectName);
        } else {
            return "Subject not found.";
        }
    }
}

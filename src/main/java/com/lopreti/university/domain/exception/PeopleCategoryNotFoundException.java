package com.lopreti.university.domain.exception;

import com.lopreti.university.domain.valueObjects.PeopleCategory;

import java.util.Arrays;

import static com.lopreti.university.domain.valueObjects.GlobalErrorCodes.PEOPLE_CATEGORY_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

public class PeopleCategoryNotFoundException extends RuntimeException {

    protected String peopleCategory;

    public PeopleCategoryNotFoundException(String peopleCategory) {
        this.peopleCategory = peopleCategory;
    }

    public String getCode() {
        return PEOPLE_CATEGORY_NOT_FOUND_ERROR_CODE.getCode();
    }

    public String getMessage() {
        if (peopleCategory != null) {
            return format("People category %s not found. Available categories: %s", peopleCategory,
                    Arrays.toString(PeopleCategory.values()));
        } else {
            return "People category not found.";
        }
    }

}

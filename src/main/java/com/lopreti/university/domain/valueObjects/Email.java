package com.lopreti.university.domain.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Email {

    private String email;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public Email() {
    }

    public Email(String email) {
        this.email = email != null ? email : "";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            //TODO THROW NEW INVALID EMAIL EXCEPTION
        }
    }

    public String getEmail() {
        return email;
    }

}

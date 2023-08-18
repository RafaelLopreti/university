package com.lopreti.university.domain.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Password {

    private String password;

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()\\-])(?=\\S+$).{8,20}$");

    public Password() {
    }

    public Password(String password) {
        this.password = password != null ? password : "";
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        if (!matcher.find()) {
            //TODO THROW NEW INVALID PASSWORD EXCEPTION
        }
    }

    public String getPassword() {
        return password;
    }

}

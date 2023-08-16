package com.lopreti.university.valueObjects;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPatternTest {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    @Test
    public void testEmailPattern() {
        List<String> validEmails = List.of(
                "test@example.com",
                "test123@test-domain.com",
                "my.name@sub.domain.com",
                "a.b.c.d@domain.co.uk"
        );

        List<String> invalidEmails = List.of(
                "invalid",
                "test@example.",
                "test@.com",
                "test@domain..com",
                ".test@domain.com",
                "test@domain.com.",
                "test@-domain.com",
                "test@domain-.com",
                "test@dom_ain.com",
                "test@domain.c",
                "test@domain.c1",
                "test@domain.com-"
        );

        testEmails(validEmails, true);
        testEmails(invalidEmails, false);
    }

    public static void testEmails(List<String> emailList, boolean expected) {
        for (String email : emailList) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            boolean isValid = matcher.matches();
            if (isValid == expected) {
                System.out.println(email + " is " + (isValid ? "valid" : "invalid") + ".");
            } else {
                System.out.println(email + " is incorrectly classified.");
            }
        }
    }

}

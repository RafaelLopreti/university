package com.lopreti.university.valueObjects;

import com.lopreti.university.domain.services.UserService;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.lopreti.university.domain.services.UserService.VALID_PASSWORD_REGEX;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordPatternTest {
    
    @Test
    public void testValidPasswords() {
        assertTrue(VALID_PASSWORD_REGEX.matcher("Abc@1234").matches());
        assertTrue(VALID_PASSWORD_REGEX.matcher("P@ssw0rd").matches());
        assertTrue(VALID_PASSWORD_REGEX.matcher("Ajc*3254").matches());
        assertTrue(VALID_PASSWORD_REGEX.matcher("Ilda_8892").matches());
    }

    @Test
    public void testInvalidPasswords() {
        assertFalse(VALID_PASSWORD_REGEX.matcher("12345678").matches());
        assertFalse(VALID_PASSWORD_REGEX.matcher("abcdefgh").matches());
        assertFalse(VALID_PASSWORD_REGEX.matcher("ABCdefgh").matches());
        assertFalse(VALID_PASSWORD_REGEX.matcher("Pass word").matches());
        assertFalse(VALID_PASSWORD_REGEX.matcher("LongPassw0rd1234567890").matches());
        assertFalse(VALID_PASSWORD_REGEX.matcher("aA@1").matches());
        assertFalse(VALID_PASSWORD_REGEX.matcher("Abc@1234 ").matches());
    }

}

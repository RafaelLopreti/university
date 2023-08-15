package com.lopreti.university.domain.valueObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private String value;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    // TODO DOCUMENT
    //    Permite valores numéricos de 0 a 9.
    //    Letras maiúsculas e minúsculas de a a z são permitidas.
    //    São permitidos sublinhado “_”, hífen “-“ e ponto “.”
    //    Ponto não é permitido no início e no final da parte local.
    //    Pontos consecutivos não são permitidos.
    //    Para a parte local, são permitidos no máximo 64 caracteres.

    public Email() {
    }

    public Email(String value) {
        this.value = value != null ? value : "";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
        if (!matcher.find()) {
            //TODO THROW NEW INVALID EMAIL EXCEPTION
        }
    }

    public String getValue() {
        return value;
    }

}

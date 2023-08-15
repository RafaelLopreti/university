package com.lopreti.university.domain.valueObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

    private String value;

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[az])(?=.*[AZ])(?=.*[@#$%^&-+=() ])(?=\\\\S+$).{8, 20}$",
                    Pattern.CASE_INSENSITIVE);

    // TODO DOCUMENT
    //    Ele contém no mínimo 8 caracteres e no máximo 20 caracteres.
    //    Contém pelo menos um dígito.
    //    Contém pelo menos um alfabeto maiúsculo.
    //    Contém pelo menos um alfabeto minúsculo.
    //    Ele contém pelo menos um caractere especial que inclui !@#$%&*()-+=^ .
    //    Não contém nenhum espaço em branco.

    public Password() {
    }

    public Password(String value) {
        this.value = value != null ? value : "";
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(value);
        if (!matcher.find()) {
            //TODO THROW NEW INVALID PASSWORD EXCEPTION
        }
    }

    public String getValue() {
        return value;
    }

}

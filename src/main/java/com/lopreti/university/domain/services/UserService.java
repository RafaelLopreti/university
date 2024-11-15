package com.lopreti.university.domain.services;

import com.lopreti.university.adapters.repositories.impl.UsersRepositoryImpl;
import com.lopreti.university.domain.entities.Users;
import com.lopreti.university.domain.exception.others.NoValidFieldException;
import com.lopreti.university.domain.exception.others.ValueCannotBeEmptyException;
import com.lopreti.university.domain.exception.user.email.EmailAlreadyExistsException;
import com.lopreti.university.domain.exception.user.email.EmailInvalidFormatException;
import com.lopreti.university.domain.exception.user.password.PasswordInvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UsersRepositoryImpl usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()\\-_*])(?=\\S+$).{8,20}$");

    public UserService(UsersRepositoryImpl usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findById(Long id) {
        return usersRepository.findById(id);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Optional<Users> findToLogin(String email, String password) {
        Optional<Users> userOptional = usersRepository.existsByEmail(email);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return userOptional;
            }
        }

        return Optional.empty();
    }

    public Users update(Long id, String key, String value) {
        Users user = findById(id);

        if (!value.isEmpty()) {
            switch (key) {
                case "email" -> user.setEmail(value);
                case "password" -> user.setPassword(passwordEncoder(value));
                default -> throw new NoValidFieldException(key);
            }
        } else {
            throw new ValueCannotBeEmptyException();
        }

        return usersRepository.save(user);
    }

    public Users update(Long id, Users usersBody) {
        Users user = findById(id);

        user.setEmail(Objects.requireNonNullElse(usersBody.getEmail(), user.getEmail()));
        user.setPassword(Objects.requireNonNullElse(passwordEncoder(usersBody.getPassword()), user.getPassword()));
        user.setStatus(Objects.requireNonNullElse(usersBody.getStatus(), user.getStatus()));

        return usersRepository.save(user);
    }

    public Users save(Users user) {
        if (existsByEmail(user.getEmail()).isEmpty()) {
            validFormat(user.getEmail(), user.getPassword());
            user.setPassword(passwordEncoder(user.getPassword()));
            return usersRepository.save(user);
        }
        throw new EmailAlreadyExistsException();
    }

    public Optional<Users> existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public void validFormat(String email, String password) {
        Matcher matcherEmail = VALID_EMAIL_ADDRESS_REGEX.matcher(email == null ? "" : email);
        if (!matcherEmail.find()) {
            throw new EmailInvalidFormatException();
        }

        Matcher matcherPassword = VALID_PASSWORD_REGEX.matcher(password == null ? "" : password);
        if (!matcherPassword.find()) {
            throw new PasswordInvalidFormatException();
        }
    }

    private String passwordEncoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}

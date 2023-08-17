package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.Email;
import com.lopreti.university.domain.valueObjects.Password;
import com.lopreti.university.domain.valueObjects.UserStatus;
import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(nullable = false, unique = true))
    })
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "password", column = @Column(nullable = false))
    })
    private Password password;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

}

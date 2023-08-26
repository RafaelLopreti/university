package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.UserStatus;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class Users extends RepresentationModel<Users> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    public Users() {}

    public Users(Long id, String email, String password, UserStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

}

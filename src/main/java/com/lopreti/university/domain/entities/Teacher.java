package com.lopreti.university.domain.entities;

import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @ManyToMany
    @JoinColumn(name = "class_code", nullable = false)
    private String classCode;

    public Long getId() {
        return id;
    }

    public void setClassList(String classCode) {
        this.classCode = classCode;
    }
}

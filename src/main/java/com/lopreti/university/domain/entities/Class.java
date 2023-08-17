package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Class code cannot be empty")
    private String code = "UNI-000";

    @ManyToMany
    @JoinColumn(name = "people_id", nullable = false)
    private List<People> peopleList = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "subjects_id", nullable = false)
    private List<Subjects> subjectsList = new ArrayList<>();

}

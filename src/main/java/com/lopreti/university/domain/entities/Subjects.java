package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Subject name cannot be empty")
    private String name;

    @ManyToMany
    @JoinColumn(name = "teacher_id")
    private List<Teacher> teacherList = new ArrayList<>();


}

package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student extends RepresentationModel<Student> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "class_code", nullable = false)
    private String classCode;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    public Student(Long id) {
        this.id = id;
    }

}

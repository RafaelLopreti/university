package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Subjects extends RepresentationModel<Subjects> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    @JoinColumn(name = "teacher_id")
    private List<Teacher> teacherList = new ArrayList<>();

    public Subjects(Long id) {
        this.id = id;
    }

}

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
public class Classes extends RepresentationModel<Classes> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code = "UNI-000";

    @ManyToMany
    @JoinColumn(name = "people_id", nullable = false)
    private List<People> peopleList = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "subjects_id", nullable = false)
    private List<Subjects> subjectsList = new ArrayList<>();

    public Classes(Long id) {
        this.id = id;
    }

}

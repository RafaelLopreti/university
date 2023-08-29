package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.Period;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Course name cannot be empty")
    private String name;

    @Column
    private Period period;

    @ManyToMany
    @JoinColumn(name = "subjects_id", nullable = false)
    private List<Subjects> subjectsList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<Subjects> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }
}

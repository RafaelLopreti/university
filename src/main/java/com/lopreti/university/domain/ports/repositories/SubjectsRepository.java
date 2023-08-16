package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Subjects;

public interface SubjectsRepository {

    public Subjects findByName(String name);

}

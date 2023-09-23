package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Classes;

import java.util.Optional;

public interface ClassRepository {

    public Classes findByCode(String classCode);

    public Optional<?> existsByCode(String classCode);

}

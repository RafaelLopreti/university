package com.lopreti.university.domain.ports.repositories;

import com.lopreti.university.domain.entities.Class;

import java.util.Optional;

public interface ClassRepository {

    public Class findByCode(String classCode);

    public Optional<?> existsByCode(String classCode);

}

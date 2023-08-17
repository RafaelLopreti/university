package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column
    @NotBlank(message = "Taxpayer Registry cannot be empty")
    private String taxpayerRegistry;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

}

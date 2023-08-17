package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Street cannot be empty")
    private String street;

    @Column
    @NotBlank(message = "Number cannot be empty")
    private String number;

    @Column
    @NotBlank(message = "City cannot be empty")
    private String city;

    @Column
    @NotBlank(message = "Neighborhood cannot be empty")
    private String neighborhood;

    @Column
    @NotBlank(message = "ZipCode cannot be empty")
    private String zipCode;

    @Column
    @NotBlank(message = "Country cannot be empty")
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

}

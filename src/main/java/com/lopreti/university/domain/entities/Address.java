package com.lopreti.university.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String city;

    @Column
    private String neighborhood;

    @Column
    private String zipCode;

    @Column
    private String country;

    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Long userId;

    public Address() {}

    public Address(Long id, String street, String number, String city, String neighborhood, String zipCode, String country, Long userId) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.country = country;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

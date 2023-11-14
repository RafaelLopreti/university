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
public class Address extends RepresentationModel<Address> {

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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    public Address(Long id) {
        this.id = id;
    }

}

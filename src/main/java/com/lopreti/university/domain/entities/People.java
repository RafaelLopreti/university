package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    public Users users;

    @Column
    @NotBlank(message = "Name cannot be empty")
    public String name;

    @Column
    @NotBlank(message = "Taxpayer Registry cannot be empty")
    public String taxpayerRegistry;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    public Address address;

    @Enumerated(EnumType.STRING)
    private PeopleCategory category;

    public People() {
    }

    public People(Long id, Users users, String name, String taxpayerRegistry, Address address) {
        this.id = id;
        this.users = users;
        this.name = name;
        this.taxpayerRegistry = taxpayerRegistry;
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaxpayerRegistry(String taxpayerRegistry) {
        this.taxpayerRegistry = taxpayerRegistry;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCategory(PeopleCategory category) {
        this.category = category;
    }
}

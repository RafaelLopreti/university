package com.lopreti.university.domain.entities;

import com.lopreti.university.domain.valueObjects.PeopleCategory;
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
public class People extends RepresentationModel<People> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    public Users user;

    @Column
    public String name;

    @Column
    public String taxpayerRegistry;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    public Address address;

    @Enumerated(EnumType.STRING)
    private PeopleCategory category;

    public People(Long id) {
        this.id = id;
    }

}

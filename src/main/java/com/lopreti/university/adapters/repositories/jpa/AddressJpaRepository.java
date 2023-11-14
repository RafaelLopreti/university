package com.lopreti.university.adapters.repositories.jpa;

import com.lopreti.university.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressJpaRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM Address WHERE street = ?1", nativeQuery = true)
    List<Address> findByStreet(String street);

    @Query(value = "SELECT * FROM Address WHERE number = ?1", nativeQuery = true)
    List<Address> findByNumber(String number);

    @Query(value = "SELECT * FROM Address WHERE city = ?1", nativeQuery = true)
    List<Address> findByCity(String city);

    @Query(value = "SELECT * FROM Address WHERE neighborhood = ?1", nativeQuery = true)
    List<Address> findByNeighborhood(String neighborhood);

    @Query(value = "SELECT * FROM Address WHERE zipCode = ?1", nativeQuery = true)
    List<Address> findByZipCode(String zipCode);

    @Query(value = "SELECT * FROM Address WHERE country = ?1", nativeQuery = true)
    List<Address> findByCountry(String country);

    @Query(value = "SELECT * FROM Address WHERE user_id = ?1", nativeQuery = true)
    List<Address> findByUserId(Long id);
}

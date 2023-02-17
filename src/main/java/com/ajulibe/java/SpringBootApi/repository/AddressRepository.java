package com.ajulibe.java.SpringBootApi.repository;

import com.ajulibe.java.SpringBootApi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // add custom methods here
}
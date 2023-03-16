package com.agrosofttechnologies.covidcaseregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrosofttechnologies.covidcaseregistration.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}

package com.agrosofttechnologies.covidcaseregistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrosofttechnologies.covidcaseregistration.entities.CovidCase;

public interface CovidCaseRepository extends JpaRepository<CovidCase, Integer> {
	public List<CovidCase> findByNationalID (String nationalID);
}

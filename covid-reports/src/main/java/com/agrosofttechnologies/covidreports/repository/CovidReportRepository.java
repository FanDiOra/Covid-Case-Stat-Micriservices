package com.agrosofttechnologies.covidreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrosofttechnologies.covidreports.entities.CovidReport;

public interface CovidReportRepository extends JpaRepository<CovidReport, String>  {
	public CovidReport findByState (String state);
}

package com.eikona.tech.repository;

import java.util.Date;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eikona.tech.entity.Driver;

@Repository
public interface DriverRepository extends DataTablesRepository<Driver, Long>{


	@Query("select dr from com.eikona.tech.entity.Driver dr where dr.dlNo =:dlNo "
			+ "and dr.dateOfBirth =:dob")
	Driver findByDlNoAndDateOfBirthCustom(String dlNo, Date dob);
	
	Driver findByDlNoAndDateOfBirth(String dlNo, Date dob);
	

}

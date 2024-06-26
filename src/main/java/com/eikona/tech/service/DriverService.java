package com.eikona.tech.service;

import java.util.List;

import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.entity.Driver;

public interface DriverService {

	Driver getByDlAndDob(String dl, String dateOfBirth);

	Driver save(Driver driver);

	Driver getById(Long id);
	
	List<Driver> getAll();

	PaginationDto<Driver> searchByField(String dlNo, String name, String dlValidUpto, String msicNo, String msicValidUpto, 
			int pageno, String sortField, String sortDir);

	

}

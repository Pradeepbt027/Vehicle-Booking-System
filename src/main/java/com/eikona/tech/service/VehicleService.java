package com.eikona.tech.service;

import java.util.List;

import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.dto.VehicleBookingDto;
import com.eikona.tech.entity.Vehicle;

public interface VehicleService {

	Vehicle getByVehicleRegnNo(String vehicleRegnNo);

	Vehicle save(Vehicle vehicle);

	Vehicle getById(Long id);

	PaginationDto<Vehicle> searchByField(String vehicleRegnNo, String chasisNumber, String ownerName,
			String insuranceUpto, String taxUpto, String maker, int pageno, String sortField, String sortDir);

	List<Vehicle> getAll();

	List<VehicleBookingDto> getAllByConstructor();

}

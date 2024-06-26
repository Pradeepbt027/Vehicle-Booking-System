package com.eikona.tech.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import com.eikona.tech.dto.VehicleBookingDto;
import com.eikona.tech.entity.Vehicle;

public interface VehicleRepository extends DataTablesRepository<Vehicle, Long>{

	Vehicle findByVehicleRegnNo(String vehicleRegnNo);

	@Query("select new com.eikona.tech.dto.VehicleBookingDto(v.vehicleRegnNo, v.chasisNumber, v.insuranceUptoStr, "
			+ "v.taxUptoStr, v.ownerName) from com.eikona.tech.entity.Vehicle v ")
	List<VehicleBookingDto> findAllByConstructorCustom();
}

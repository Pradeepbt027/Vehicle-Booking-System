package com.eikona.tech.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.eikona.tech.constants.ApplicationConstants;
import com.eikona.tech.constants.NumberConstants;
import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.dto.VehicleBookingDto;
import com.eikona.tech.entity.Vehicle;
import com.eikona.tech.repository.VehicleRepository;
import com.eikona.tech.service.VehicleService;
import com.eikona.tech.util.GeneralSpecificationUtil;
import com.eikona.tech.util.VehicleUtil;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private GeneralSpecificationUtil<Vehicle> generalSpecificationVehicle;
	
	@Autowired
	private VehicleUtil vehicleUtil;

	@Override
	public Vehicle getByVehicleRegnNo(String vehicleRegnNo) {
		
		Vehicle vehicle = vehicleRepository.findByVehicleRegnNo(vehicleRegnNo);
		if(null ==vehicle) {
			
			vehicle = new Vehicle(vehicleRegnNo);
			String vehicleObjStr = vehicleUtil.getVehicleDetailsByVehicleNo(vehicle);
			
			
			JSONObject obj = XML.toJSONObject(vehicleObjStr);
			
			vehicle  = new Vehicle(obj.getString("rc_regn_no"), obj.getString("rc_vh_class_desc"), obj.getString("rc_chasi_no"),obj.getString("rc_eng_no"),obj.getString("rc_maker_desc"),obj.getString("rc_insurance_upto"),
					obj.getString("rc_tax_upto"), obj.getString("rc_fit_upto"), obj.getString("rc_manu_month_yr"),obj.getString("rc_owner_name"),obj.getString("rc_permanent_address"),obj.getString("rc_present_address"));
			
			save(vehicle);
		}
		
		return vehicle;
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		
		SimpleDateFormat sdf= new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_OF_US);
		try {
			if(null != vehicle.getInsuranceUptoStr())
				vehicle.setInsuranceUpto(sdf.parse(vehicle.getInsuranceUptoStr()));
			if(null != vehicle.getTaxUptoStr())
				vehicle.setTaxUpto(sdf.parse(vehicle.getTaxUptoStr()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle getById(Long id) {
		return vehicleRepository.findById(id).get();
	}

	@Override
	public PaginationDto<Vehicle> searchByField(String vehicleRegnNo, String chasisNumber, String ownerName,
			String insuranceUpto, String taxUpto, String maker, int pageno, String sortField, String sortDir) {
		
		if (null == sortDir || sortDir.isEmpty()) {
			sortDir = ApplicationConstants.ASC;
		}
		if (null == sortField || sortField.isEmpty()) {
			sortField = ApplicationConstants.ID;
		}
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Page<Vehicle> page = getPaginatedData(vehicleRegnNo, chasisNumber, ownerName, insuranceUpto, taxUpto, maker, pageno, sort);
		List<Vehicle> accessLevelList = page.getContent();

		sortDir = (ApplicationConstants.ASC.equalsIgnoreCase(sortDir)) ? ApplicationConstants.DESC : ApplicationConstants.ASC;
		PaginationDto<Vehicle> dtoList = new PaginationDto<Vehicle>(accessLevelList, page.getTotalPages(),
				page.getNumber() + NumberConstants.ONE, page.getSize(), page.getTotalElements(), page.getTotalElements(), sortDir,
				ApplicationConstants.SUCCESS, ApplicationConstants.MSG_TYPE_S);
		return dtoList;
	}


	private Page<Vehicle> getPaginatedData(String vehicleRegnNo, String chasisNumber, String ownerName,
			String insuranceUpto, String taxUpto, String maker, int pageno, Sort sort) {
		
		Pageable pageable = PageRequest.of(pageno - NumberConstants.ONE, NumberConstants.TEN, sort);

		Specification<Vehicle> regNoSpc = generalSpecificationVehicle.stringSpecification(vehicleRegnNo, "vehicleRegnNo");
		Specification<Vehicle> chasisNoSpc = generalSpecificationVehicle.stringSpecification(chasisNumber, "ownerName");
		Specification<Vehicle> holderSpc = generalSpecificationVehicle.stringSpecification(ownerName, "ownerName");
		Specification<Vehicle> insUptoSpc = generalSpecificationVehicle.stringSpecification(insuranceUpto, "vehicleClass");
		Specification<Vehicle> taxUptoSpc = generalSpecificationVehicle.stringSpecification(taxUpto, "chasisNumber");
		Specification<Vehicle> makerSpc = generalSpecificationVehicle.stringSpecification(maker, "engineNumber");
		
		Page<Vehicle> page = vehicleRepository.findAll(regNoSpc.and(chasisNoSpc).and(holderSpc).and(insUptoSpc).and(taxUptoSpc).and(makerSpc), pageable);
		
		return page;
	}
	
	@Override
	public List<Vehicle> getAll() {
		return (List<Vehicle>) vehicleRepository.findAll();
	}

	@Override
	public List<VehicleBookingDto> getAllByConstructor() {
		
		return (List<VehicleBookingDto>) vehicleRepository.findAllByConstructorCustom();
	}
}

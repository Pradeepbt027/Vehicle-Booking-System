package com.eikona.tech.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.entity.Vehicle;
import com.eikona.tech.service.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/vehicle")
	@PreAuthorize("hasAuthority('vehicle_view')")
	public String vehicleListPage(Model model) {
		
		model.addAttribute("pageno", 1);
		return "vehicle/vehicle_list";
	}
	
	@GetMapping("/vehicle/{page}")
	@PreAuthorize("hasAuthority('vehicle_view')")
	public String areaListPage(@PathVariable(value = "page") long page, Model model) {
		
		model.addAttribute("pageno", page);
		return "vehicle/vehicle_list";
	}
	
	@GetMapping("/get-vehicle/by-id/{pageno}/{id}")
	@PreAuthorize("hasAuthority('vehicle_create')")
	public String getDriverById(@PathVariable(value = "pageno") Long pageno, @PathVariable(value = "id") Long id, Model model) {
		Vehicle vehicle = vehicleService.getById(id);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("pageno", pageno);
		model.addAttribute("title", "Vehicle Details");
		return "vehicle/vehicle_view";
	}

	@GetMapping("/vehicle/new")
	@PreAuthorize("hasAuthority('vehicle_create')")
	public String newVehicle(Model model) {

		model.addAttribute("title", "Add Vehicle");
		return "vehicle/add_vehicle";
	}
	
	@PostMapping("/get-vehicle/by-regn-no")
	@PreAuthorize("hasAuthority('vehicle_create')")
	public String newDriver(String vehicleRegnNo, Model model) {
		
			// call the govt. rest api and get the Vehicle obj.
		Vehicle vehicle= null;//vehicleService.getByVehicleRegnNo(vehicleRegnNo);
			
		if(null == vehicle) {
			vehicle=new Vehicle();
			vehicle.setVehicleRegnNo(vehicleRegnNo);
			vehicle.setChasisNumber("CFTR123456");
			vehicle.setOwnerName("Chetan M");
			vehicle.setInsuranceUptoStr("31-08-2024");
			vehicle.setTaxUptoStr("30-09-2024");
			vehicle.setMakerAndDescription("Bharat Benz H08");
		}
			
			
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("title", "Vehicle Details");
		return "vehicle/vehicle_new";
	}

	@PostMapping("/vehicle/add")
	@PreAuthorize("hasAuthority('vehicle_create')")
	public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle, String title,
			Model model, Principal principal) {
			if (null == vehicle.getId())
				vehicleService.save(vehicle);
			else {
				Vehicle vehicleObj = vehicleService.getById(vehicle.getId());
				vehicle.setCreatedBy(vehicleObj.getCreatedBy());
				vehicle.setCreatedDate(vehicleObj.getCreatedDate());
				vehicleService.save(vehicle);
			}
			return "redirect:/vehicle";
	}

	@RequestMapping(value = "/api/search/vehicle", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('vehicle_view')")
	public @ResponseBody PaginationDto<Vehicle> search(String vehicleRegnNo, String chasisNumber, String ownerName, String insuranceUpto,
			String taxUpto,String maker, int pageno, String sortField, String sortDir) {
		
		PaginationDto<Vehicle> dtoList = vehicleService.searchByField(vehicleRegnNo, chasisNumber, ownerName, insuranceUpto,
				taxUpto, maker, pageno, sortField, sortDir);
		
		return dtoList;
	}

}

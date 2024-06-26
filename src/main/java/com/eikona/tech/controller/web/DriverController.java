package com.eikona.tech.controller.web;

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
import com.eikona.tech.entity.Driver;
import com.eikona.tech.service.DriverService;

@Controller
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	
	
	@GetMapping("/driver")
	@PreAuthorize("hasAuthority('driver_view')")
	public String driverListPage(Model model) {
		
		model.addAttribute("pageno", 1);
		return "driver/driver_list";
	}

	@GetMapping("/driver/{page}")
	@PreAuthorize("hasAuthority('driver_view')")
	public String areaListPage(@PathVariable(value = "page") long page, Model model) {
		
		model.addAttribute("pageno", page);
		return "driver/driver_list";
	}
	
	@GetMapping("/driver/new")
	@PreAuthorize("hasAuthority('driver_create')")
	public String newDriver(Model model) {
		model.addAttribute("title", "Add Driver");
		return "driver/add_driver";
	}
	
	@PostMapping("/get-driver/by-dlno-dob")
	@PreAuthorize("hasAuthority('driver_create')")
	public String newDriver(String dlNo, String dateOfBirth, Model model) {
		
		// call the govt. rest api and get the Vehicle obj.
		Driver driver = driverService.getByDlAndDob(dlNo, dateOfBirth);
		if(null == driver) {
			driver = new Driver();
			driver.setDlNo(dlNo);
			driver.setNameOfHolder("Abhisekh");
			driver.setDobStr(dateOfBirth);
			driver.setDlValidTillStr("31-10-2024");
			driver.setMsicNo("345670");
			driver.setMsicValidTillStr("31-07-2024");
		}
		
		model.addAttribute("driver", driver);
		model.addAttribute("title", "Driver Details");
		return "driver/driver_new";
	}
	
	
	@GetMapping("/get-driver/by-id/{pageno}/{id}")
	@PreAuthorize("hasAuthority('driver_create')")
	public String getDriverById(@PathVariable(value = "pageno") Long pageno, @PathVariable(value = "id") Long id, Model model) {
		Driver driver = driverService.getById(id);
		model.addAttribute("driver", driver);
		model.addAttribute("pageno", pageno);
		model.addAttribute("title", "Driver Details");
		return "driver/driver_view";
	}

	@PostMapping("/driver/add")
	@PreAuthorize("hasAuthority('driver_create')")
	public String saveDriver(@ModelAttribute("driver") Driver driver,  String title,
			Model model) {
			if (null == driver.getId())
				driverService.save(driver);
			else {
				Driver driverObj = driverService.getById(driver.getId());
				driver.setCreatedBy(driverObj.getCreatedBy());
				driver.setCreatedDate(driverObj.getCreatedDate());
				driverService.save(driver);
			}
			return "redirect:/driver";
	}

	@RequestMapping(value = "/api/search/driver", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('driver_view')")
	public @ResponseBody PaginationDto<Driver> search(String dlNo, String name, String dlValidUpto, String msicNo, String msicValidUpto, 
			int pageno, String sortField, String sortDir) {
		
		PaginationDto<Driver> dtoList = driverService.searchByField(dlNo, name, dlValidUpto, msicNo, msicValidUpto, pageno, sortField, sortDir);
		return dtoList;
	}
}

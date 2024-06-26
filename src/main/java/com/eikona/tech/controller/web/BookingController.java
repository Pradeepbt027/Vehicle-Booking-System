package com.eikona.tech.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
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
import com.eikona.tech.dto.SlotDto;
import com.eikona.tech.dto.VehicleBookingDto;
import com.eikona.tech.entity.Booking;
import com.eikona.tech.entity.Container;
import com.eikona.tech.entity.Driver;
import com.eikona.tech.service.BookingService;
import com.eikona.tech.service.ContainerService;
import com.eikona.tech.service.DriverService;
import com.eikona.tech.service.VehicleService;

@Controller
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ContainerService containerService;
	
	@Autowired
	private DriverService driverService;
	
	@GetMapping("/booking")
	@PreAuthorize("hasAuthority('booking_view')")
	public String bookingListPage(Model model) {
		
		model.addAttribute("pageno", 1);
		return "booking/booking_list";
	}
	
	@GetMapping("/booking/{page}")
	@PreAuthorize("hasAuthority('booking_view')")
	public String bookingListPage(@PathVariable(value = "page") long page, Model model) {
		
		model.addAttribute("pageno", page);
		return "booking/booking_list";
	}
	
	@GetMapping("/booking/new")
	@PreAuthorize("hasAuthority('booking_create')")
	public String newBooking(Model model) {
		model.addAttribute("booking", new Booking());
		model.addAttribute("title", "New Booking");
		return "booking/booking_new";
	}
	
	@GetMapping("/booking/edit/{id}")
	@PreAuthorize("hasAuthority('booking_view')")
	public String editBooking(@PathVariable(value = "id") long id, Model model) {
		
		Booking booking = bookingService.getById(id);
		model.addAttribute("booking", booking);
		model.addAttribute("title", "Edit Booking");
		return "booking/booking_new";
	}
	
	@PostMapping("/booking/add")
	@PreAuthorize("hasAuthority('booking_create')")
	public String saveBooking(@ModelAttribute("booking") Booking booking, Model model) {

		
		bookingService.save(booking);
		
		return "redirect:/booking";
	}
	
	@GetMapping("/api/get/all-vehicles")
	@PreAuthorize("hasAuthority('booking_view')")
	public @ResponseBody List<VehicleBookingDto> getAllVehicles() {
		
		List<VehicleBookingDto> vehicles = vehicleService.getAllByConstructor();
		
		return vehicles;
	}
	
	@GetMapping("/api/get/all-container")
	@PreAuthorize("hasAuthority('booking_view')")
	public @ResponseBody List<Container> getAllContainer() {
		
		List<Container> container = containerService.getAll();
		
		return container;
	}
	
	@GetMapping("/api/get/all-driver")
	@PreAuthorize("hasAuthority('driver_view')")
	public @ResponseBody List<Driver> getAllDriver() {
		
		List<Driver> driver = driverService.getAll();
		
		return driver;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/api/get/booking-slots")
	@PreAuthorize("hasAuthority('booking_create')")
	public @ResponseBody JSONObject getBookingSlots(String pickUpDate, String dropDate) {
		
		List<SlotDto> dropSlot =  bookingService.findDropSlotCountByDateStr(dropDate);
		List<SlotDto> pickupSlot =  bookingService.findPickUpSlotCountByDateStr(pickUpDate);
		
		JSONObject slotObj = new JSONObject();
		
		if(!pickUpDate.isEmpty())
			slotObj.put("pickUpSlotData", getSlotDto(pickupSlot));
		else
			slotObj.put("pickUpSlotData", pickupSlot);
		
		if(!dropDate.isEmpty())
			slotObj.put("dropSlotData", getSlotDto(dropSlot));
		else
			slotObj.put("dropSlotData", dropSlot);
		
		
		return slotObj;
	}
	
	private List<SlotDto> getSlotDto(List<SlotDto> slotDtoListData){
		List<String> slots = List.of("01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00",
				"09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00",
				"17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00");
		
		List<SlotDto> slotDtoList = new ArrayList<>();
		for(String slot : slots) {
			SlotDto slotDto = new SlotDto(slot, 0l);
			for(SlotDto slotDto2: slotDtoListData) {
				if(slot.equalsIgnoreCase(slotDto2.getTime())) {
					//slotDto = new SlotDto(slot, slotDto2.getCount());
					slotDto = new SlotDto(slot, 10l);
				}
			}
			slotDtoList.add(slotDto);
		}
		
		return slotDtoList;
	}
	
	@RequestMapping(value = "/api/search/booking", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('booking_view')")
	public @ResponseBody PaginationDto<Booking> search(String apptDate, String bookingRef, String apptTime, String tripType, String vehicleNo, 
			int pageno, String sortField, String sortDir) {
		
		PaginationDto<Booking> dtoList = bookingService.searchByField(apptDate, bookingRef, apptTime, tripType, vehicleNo, pageno, sortField, sortDir);
		return dtoList;
	}
}

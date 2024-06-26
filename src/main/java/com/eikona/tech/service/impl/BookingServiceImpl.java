package com.eikona.tech.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

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
import com.eikona.tech.dto.SlotDto;
import com.eikona.tech.entity.Booking;
import com.eikona.tech.repository.BookingRepository;
import com.eikona.tech.service.BookingService;
import com.eikona.tech.util.GeneralSpecificationUtil;

@Service
public class BookingServiceImpl implements BookingService {
	
	
	@Autowired
	private GeneralSpecificationUtil<Booking> generalSpecificationBooking;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	@Override
	public void save(Booking booking) {
		
		if (null != booking.getId()) {
			Booking bookingObj = bookingRepository.findById(booking.getId()).get();
			booking.setCreatedBy(bookingObj.getCreatedBy());
			booking.setCreatedDate(bookingObj.getCreatedDate());
			
			if(null == booking.getDropReportingTimeStr()) {
				booking.setDropReportingTimeStr(bookingObj.getDropReportingTimeStr());
			}
			if(null == booking.getPickUpReportingTimeStr()) {
				booking.setPickUpReportingTimeStr(bookingObj.getPickUpReportingTimeStr());
			}
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_OF_US);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		try {
			
			if(null != booking.getDropBookingDateStr())
				booking.setDropBookingDate(dateFormat.parse(booking.getDropBookingDateStr()));
			if(null != booking.getPickUpBookingDateStr())
				booking.setPickUpBookingDate(dateFormat.parse(booking.getPickUpBookingDateStr()));
			if(null != booking.getDropReportingTimeStr())
				booking.setDropReportingTime(timeFormat.parse(booking.getDropReportingTimeStr()));
			if(null != booking.getPickUpReportingTimeStr())
				booking.setPickUpReportingTime(timeFormat.parse(booking.getPickUpReportingTimeStr()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bookingRepository.save(booking);
	}


	@Override
	public Booking getById(Long id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if(booking.isPresent())
			return booking.get();
		else
			return null;
	}

	@Override
	public PaginationDto<Booking> searchByField(String apptDate, String bookingRef, String apptTime, String tripType,
			String vehicleNo, int pageno, String sortField, String sortDir) {
		
		String[] apptTimeArray;
		if(!apptTime.isEmpty()) {
			apptTimeArray = apptTime.split(":");
			apptTime = apptTimeArray[0]+":00";
		}
		
		
		if (null == sortDir || sortDir.isEmpty()) {
			sortDir = ApplicationConstants.ASC;
		}
		if (null == sortField || sortField.isEmpty()) {
			sortField = ApplicationConstants.ID;
		}
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Page<Booking> page = getPaginatedData(apptDate, bookingRef, apptTime, tripType, vehicleNo, pageno, sort);
		List<Booking> accessLevelList = page.getContent();

		sortDir = (ApplicationConstants.ASC.equalsIgnoreCase(sortDir)) ? ApplicationConstants.DESC : ApplicationConstants.ASC;
		PaginationDto<Booking> dtoList = new PaginationDto<Booking>(accessLevelList, page.getTotalPages(),
				page.getNumber() + NumberConstants.ONE, page.getSize(), page.getTotalElements(), page.getTotalElements(), sortDir,
				ApplicationConstants.SUCCESS, ApplicationConstants.MSG_TYPE_S);
		
		return dtoList;
	}


	private Page<Booking> getPaginatedData(String apptDate, String bookingRef, String apptTime, String tripType,
			String vehicleNo,  int pageno, Sort sort) {
		
		Pageable pageable = PageRequest.of(pageno - NumberConstants.ONE, NumberConstants.TEN, sort);

		Specification<Booking> apptDateSpc = generalSpecificationBooking.stringSpecification(apptDate, "pickUpBookingDateStr");
		Specification<Booking> bookingRefSpc = generalSpecificationBooking.stringSpecification(bookingRef, "bookingRef");
		Specification<Booking> apptTimeSpc = generalSpecificationBooking.stringSpecification(apptTime, "pickUpReportingTimeStr");
		Specification<Booking> tripTypeSpc = generalSpecificationBooking.stringSpecification(tripType, "tripType");
		Specification<Booking> pickUpVehlNoSpc = generalSpecificationBooking.stringSpecification(vehicleNo, "pickUpVehicleNo");
		Specification<Booking> dropVehlNoSpc = generalSpecificationBooking.stringSpecification(vehicleNo, "dropVehicleNo");
		
		Page<Booking> page = bookingRepository.findAll(apptDateSpc.and(bookingRefSpc).and(apptTimeSpc).and(tripTypeSpc).and(pickUpVehlNoSpc.or(dropVehlNoSpc)), pageable);
		
		return page;
	}


	@Override
	public List<SlotDto> findDropSlotCountByDateStr(String dateStr) {
		return bookingRepository.findDropSlotCountByDateStrCustom(dateStr);
	}


	@Override
	public List<SlotDto> findPickUpSlotCountByDateStr(String dateStr) {
		return bookingRepository.findPickUpSlotCountByDateStrCustom(dateStr);
	}


	

}

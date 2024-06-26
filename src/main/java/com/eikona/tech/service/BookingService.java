package com.eikona.tech.service;

import java.util.List;

import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.dto.SlotDto;
import com.eikona.tech.entity.Booking;

public interface BookingService {

	PaginationDto<Booking> searchByField(String apptDate, String bookingRef, String apptTime, String tripType,
			String vehicleNo, int pageno, String sortField, String sortDir);

	void save(Booking booking);

	Booking getById(Long id);

	List<SlotDto> findDropSlotCountByDateStr(String dropDate);

	List<SlotDto> findPickUpSlotCountByDateStr(String pickUpDate);

}

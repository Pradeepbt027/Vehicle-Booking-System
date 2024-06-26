package com.eikona.tech.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eikona.tech.dto.SlotDto;
import com.eikona.tech.entity.Booking;

@Repository
public interface BookingRepository extends DataTablesRepository<Booking, Long>{

	@Query("select new com.eikona.tech.dto.SlotDto(b.dropReportingTimeStr, count(b.id)) from com.eikona.tech.entity.Booking b"
			+ " where b.dropBookingDateStr =:dateStr group by b.dropReportingTimeStr")
	List<SlotDto> findDropSlotCountByDateStrCustom(String dateStr);

	@Query("select new com.eikona.tech.dto.SlotDto(b.pickUpReportingTimeStr, count(b.id)) from com.eikona.tech.entity.Booking b"
			+ " where b.pickUpBookingDateStr =:dateStr group by b.pickUpReportingTimeStr")
	List<SlotDto> findPickUpSlotCountByDateStrCustom(String dateStr);


}

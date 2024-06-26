package com.eikona.tech.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "et_booking")
public class Booking  extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "booking_ref")
	private String bookingRef;
	
	@Column(name = "pick_vehicle_no")
	private String pickUpVehicleNo;
	
	@Column(name = "drop_vehicle_no")
	private String dropVehicleNo;
	
	@Column(name = "pick_container_no")
	private String pickUpContainerNo;
	
	@Column(name = "drop_container_no")
	private String dropContainerNo;
	
	@Column(name = "pick_up_dl_no")
	private String pickUpDlNo;
	
	@Column(name = "drop_dl_no")
	private String dropDlNo;
	
	@Column(name = "pickup_booking_date")
	private Date pickUpBookingDate;
	
	@Column(name = "pickup_booking_date_str")
	private String pickUpBookingDateStr;
	
	@Column(name = "drop_booking_date")
	private Date dropBookingDate;
	
	@Column(name = "drop_booking_date_str")
	private String dropBookingDateStr;
	
	@Column(name = "pickup_reporting_time")
	private Date pickUpReportingTime;
	
	@Column(name = "pickup_reporting_time_str")
	private String pickUpReportingTimeStr;
	
	@Column(name = "drop_reporting_time")
	private Date dropReportingTime;
	
	@Column(name = "drop_reporting_time_str")
	private String dropReportingTimeStr;
	
	@Column(name = "trip_type")
	private String tripType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(String bookingRef) {
		this.bookingRef = bookingRef;
	}

	public String getPickUpVehicleNo() {
		return pickUpVehicleNo;
	}

	public void setPickUpVehicleNo(String pickUpVehicleNo) {
		this.pickUpVehicleNo = pickUpVehicleNo;
	}

	public String getDropVehicleNo() {
		return dropVehicleNo;
	}

	public void setDropVehicleNo(String dropVehicleNo) {
		this.dropVehicleNo = dropVehicleNo;
	}

	public String getDropContainerNo() {
		return dropContainerNo;
	}

	public void setDropContainerNo(String dropContainerNo) {
		this.dropContainerNo = dropContainerNo;
	}

	public String getPickUpContainerNo() {
		return pickUpContainerNo;
	}

	public void setPickUpContainerNo(String pickUpContainerNo) {
		this.pickUpContainerNo = pickUpContainerNo;
	}

	public String getPickUpDlNo() {
		return pickUpDlNo;
	}

	public void setPickUpDlNo(String pickUpDlNo) {
		this.pickUpDlNo = pickUpDlNo;
	}

	public String getDropDlNo() {
		return dropDlNo;
	}

	public void setDropDlNo(String dropDlNo) {
		this.dropDlNo = dropDlNo;
	}

	public Date getPickUpBookingDate() {
		return pickUpBookingDate;
	}

	public void setPickUpBookingDate(Date pickUpBookingDate) {
		this.pickUpBookingDate = pickUpBookingDate;
	}

	public String getPickUpBookingDateStr() {
		return pickUpBookingDateStr;
	}

	public void setPickUpBookingDateStr(String pickUpBookingDateStr) {
		this.pickUpBookingDateStr = pickUpBookingDateStr;
	}

	public Date getDropBookingDate() {
		return dropBookingDate;
	}

	public void setDropBookingDate(Date dropBookingDate) {
		this.dropBookingDate = dropBookingDate;
	}

	public String getDropBookingDateStr() {
		return dropBookingDateStr;
	}

	public void setDropBookingDateStr(String dropBookingDateStr) {
		this.dropBookingDateStr = dropBookingDateStr;
	}

	public Date getPickUpReportingTime() {
		return pickUpReportingTime;
	}

	public void setPickUpReportingTime(Date pickUpReportingTime) {
		this.pickUpReportingTime = pickUpReportingTime;
	}

	public String getPickUpReportingTimeStr() {
		return pickUpReportingTimeStr;
	}

	public void setPickUpReportingTimeStr(String pickUpReportingTimeStr) {
		this.pickUpReportingTimeStr = pickUpReportingTimeStr;
	}

	public Date getDropReportingTime() {
		return dropReportingTime;
	}

	public void setDropReportingTime(Date dropReportingTime) {
		this.dropReportingTime = dropReportingTime;
	}

	public String getDropReportingTimeStr() {
		return dropReportingTimeStr;
	}

	public void setDropReportingTimeStr(String dropReportingTimeStr) {
		this.dropReportingTimeStr = dropReportingTimeStr;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
}

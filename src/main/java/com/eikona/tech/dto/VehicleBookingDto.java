package com.eikona.tech.dto;

public class VehicleBookingDto {

	private String value;
	private String chasisNumber;
	private String insuranceUpto;
	private String taxUpto;
	private String ownerName;
	
	public String getValue() {
		if(null == value)
			return "       ";
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getChasisNumber() {
		if(null == chasisNumber)
			return "-----";
		return chasisNumber;
	}
	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}
	public String getInsuranceUpto() {
		if(null == insuranceUpto)
			return "-----";
		return insuranceUpto;
	}
	public void setInsuranceUpto(String insuranceUpto) {
		this.insuranceUpto = insuranceUpto;
	}
	public String getTaxUpto() {
		if(null == taxUpto)
			return "-----";
		
		return taxUpto;
	}
	public void setTaxUpto(String taxUpto) {
		this.taxUpto = taxUpto;
	}
	public String getOwnerName() {
		if(null == ownerName)
			return "-----";
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public VehicleBookingDto() {
		super();
	}
	public VehicleBookingDto(String value, String chasisNumber, String insuranceUpto, String taxUpto,
			String ownerName) {
		super();
		this.value = value;
		this.chasisNumber = chasisNumber;
		this.insuranceUpto = insuranceUpto;
		this.taxUpto = taxUpto;
		this.ownerName = ownerName;
	}
	
}

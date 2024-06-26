package com.eikona.tech.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "et_vehicle")
public class Vehicle extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "vehicle_regn_no")
	private String vehicleRegnNo;
	
	@Column(name = "vehicle_class")
	private String vehicleClass;
	
	@Column(name = "chasis_number")
	private String chasisNumber;
	
	@Column(name = "engine_number")
	private String engineNumber;
	
	@Column(name = "maker_and_description")
	private String makerAndDescription;
	
	@Column(name = "insurance_upto")
	private Date insuranceUpto;
	
	@Column(name = "insurance_upto_str")
	private String insuranceUptoStr;
	
	@Column(name = "tax_upto")
	private Date taxUpto;
	
	@Column(name = "tax_upto_str")
	private String taxUptoStr;
	
	@Column(name = "fitness_upto")
	private String fitnessUpto;
	
	@Column(name = "mfg_moyy")
	private String mfgMOYY;
	
	@Column(name = "owner_name")
	private String ownerName;
	
	@Column(name = "permanent_address")
	private String permanentAddress;
	
	@Column(name = "current_address")
	private String currentAddress;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleRegnNo() {
		return vehicleRegnNo;
	}

	public void setVehicleRegnNo(String vehicleRegnNo) {
		this.vehicleRegnNo = vehicleRegnNo;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getChasisNumber() {
		return chasisNumber;
	}

	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getMakerAndDescription() {
		return makerAndDescription;
	}

	public void setMakerAndDescription(String makerAndDescription) {
		this.makerAndDescription = makerAndDescription;
	}

	public Date getInsuranceUpto() {
		return insuranceUpto;
	}

	public void setInsuranceUpto(Date insuranceUpto) {
		this.insuranceUpto = insuranceUpto;
	}

	public Date getTaxUpto() {
		return taxUpto;
	}

	public void setTaxUpto(Date taxUpto) {
		this.taxUpto = taxUpto;
	}

	public String getFitnessUpto() {
		return fitnessUpto;
	}

	public void setFitnessUpto(String fitnessUpto) {
		this.fitnessUpto = fitnessUpto;
	}

	public String getMfgMOYY() {
		return mfgMOYY;
	}

	public void setMfgMOYY(String mfgMOYY) {
		this.mfgMOYY = mfgMOYY;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getInsuranceUptoStr() {
		return insuranceUptoStr;
	}

	public void setInsuranceUptoStr(String insuranceUptoStr) {
		this.insuranceUptoStr = insuranceUptoStr;
	}

	public String getTaxUptoStr() {
		return taxUptoStr;
	}

	public void setTaxUptoStr(String taxUptoStr) {
		this.taxUptoStr = taxUptoStr;
	}

	public Vehicle(String vehicleRegnNo, String chasisNumber, String makerAndDescription, String insuranceUptoStr,
			String taxUptoStr, String ownerName) {
		super();
		this.vehicleRegnNo = vehicleRegnNo;
		this.chasisNumber = chasisNumber;
		this.makerAndDescription = makerAndDescription;
		this.insuranceUptoStr = insuranceUptoStr;
		this.taxUptoStr = taxUptoStr;
		this.ownerName = ownerName;
	}
	
	
	
	public Vehicle(String vehicleRegnNo, String vehicleClass, String chasisNumber, String engineNumber,
			String makerAndDescription, String insuranceUptoStr, String taxUptoStr, String fitnessUpto, String mfgMOYY,
			String ownerName, String permanentAddress, String currentAddress) {
		super();
		this.vehicleRegnNo = vehicleRegnNo;
		this.vehicleClass = vehicleClass;
		this.chasisNumber = chasisNumber;
		this.engineNumber = engineNumber;
		this.makerAndDescription = makerAndDescription;
		this.insuranceUptoStr = insuranceUptoStr;
		this.taxUptoStr = taxUptoStr;
		this.fitnessUpto = fitnessUpto;
		this.mfgMOYY = mfgMOYY;
		this.ownerName = ownerName;
		this.permanentAddress = permanentAddress;
		this.currentAddress = currentAddress;
	}

	public Vehicle(String vehicleRegnNo) {
		super();
		this.vehicleRegnNo = vehicleRegnNo;
	}

	public Vehicle() {
		super();
	}

}

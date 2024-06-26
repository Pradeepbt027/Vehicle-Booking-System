package com.eikona.tech.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "et_driver")
public class Driver extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "dl_no")
	private String dlNo;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "dob_str")
	private String dobStr;
	
	@Column(name = "dl_issue_date")
	private String dlIssueDate;
	
	@Column(name = "issuing_authority")
	private String issuingAuthority;
	
	@Column(name = "dl_valid_till")
	private Date dlValidTill;
	
	@Column(name = "dl_valid_till_str")
	private String dlValidTillStr;
	
	@Column(name = "name_of_holder")
	private String nameOfHolder;
	
	@Column(name = "permitted_class")
	private String permittedClass;
	
	@Column(name = "msic_no")
	private String msicNo;
	
	@Column(name = "msic_valid_till")
	private Date msicValidTill;
	
	@Column(name = "msic_valid_till_str")
	private String msicValidTillStr;
	
	@Column(name = "msic_issued_by")
	private String msicIssuedBy;
	
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

	public String getDlNo() {
		return dlNo;
	}

	public void setDlNo(String dlNo) {
		this.dlNo = dlNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDlIssueDate() {
		return dlIssueDate;
	}

	public void setDlIssueDate(String dlIssueDate) {
		this.dlIssueDate = dlIssueDate;
	}

	public String getDlValidTillStr() {
		return dlValidTillStr;
	}

	public void setDlValidTillStr(String dlValidTillStr) {
		this.dlValidTillStr = dlValidTillStr;
	}


	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public Date getDlValidTill() {
		return dlValidTill;
	}

	public void setDlValidTill(Date dlValidTill) {
		this.dlValidTill = dlValidTill;
	}

	public String getNameOfHolder() {
		return nameOfHolder;
	}

	public void setNameOfHolder(String nameOfHolder) {
		this.nameOfHolder = nameOfHolder;
	}

	public String getPermittedClass() {
		return permittedClass;
	}

	public void setPermittedClass(String permittedClass) {
		this.permittedClass = permittedClass;
	}

	public String getMsicNo() {
		return msicNo;
	}

	public void setMsicNo(String msicNo) {
		this.msicNo = msicNo;
	}

	public String getMsicValidTillStr() {
		return msicValidTillStr;
	}

	public void setMsicValidTillStr(String msicValidTillStr) {
		this.msicValidTillStr = msicValidTillStr;
	}

	public Date getMsicValidTill() {
		return msicValidTill;
	}

	public void setMsicValidTill(Date msicValidTill) {
		this.msicValidTill = msicValidTill;
	}

	public String getMsicIssuedBy() {
		return msicIssuedBy;
	}

	public void setMsicIssuedBy(String msicIssuedBy) {
		this.msicIssuedBy = msicIssuedBy;
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

	public String getDobStr() {
		return dobStr;
	}

	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}

	
}

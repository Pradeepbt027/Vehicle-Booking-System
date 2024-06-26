package com.eikona.tech.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.eikona.tech.entity.Driver;
import com.eikona.tech.repository.DriverRepository;
import com.eikona.tech.service.DriverService;
import com.eikona.tech.util.CalendarUtil;
import com.eikona.tech.util.DriverUtil;
import com.eikona.tech.util.GeneralSpecificationUtil;

@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	private CalendarUtil calendarUtil;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private GeneralSpecificationUtil<Driver> generalSpecificationDriver;
	
	@Autowired
	private DriverUtil driverUtil;

	@Override
	public Driver getByDlAndDob(String dlNo, String dateOfBirth) {
		SimpleDateFormat format = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_OF_US);
		
		Driver driver = new Driver();
		try {
			
//			driver = driverRepository.findByDlNoAndDateOfBirth(dlNo, format.parse(dateOfBirth));
			driver = driverRepository.findByDlNoAndDateOfBirthCustom(dlNo, format.parse(dateOfBirth));
		
			if(null == driver) {
				driver = new Driver();
				driver.setDlNo(dlNo);
				driver.setDobStr(dateOfBirth);
				driverUtil.getDriverDetails(driver);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null == driver)
			driver = new Driver();
		
		return driver;
	}

	@Override
	public Driver save(Driver driver) {
		SimpleDateFormat sdf= new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_OF_US);
		try {
			driver.setDlValidTill(sdf.parse(driver.getDlValidTillStr()));
			driver.setMsicValidTill(sdf.parse(driver.getMsicValidTillStr()));
			driver.setDateOfBirth(sdf.parse(driver.getDobStr()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		driver = driverRepository.save(driver); 
		return driver;
	}

	@Override
	public Driver getById(Long id) {
		
		Driver driver = driverRepository.findById(id).get();
		return driver;
	}
	
	@Override
	public List<Driver> getAll() {
		return (List<Driver>) driverRepository.findAll();
	}

	@Override
	public PaginationDto<Driver> searchByField(String dlNo, String name, String dlValidUptoStr, String msicNo, String msicValidUptoStr, 
			int pageno, String sortField, String sortDir) {
		
		SimpleDateFormat format = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_OF_US);
//		if(null ==dLValidUptoStr || dLValidUptoStr.isEmpty())
//			dLValidUptoStr = format.format(new Date());
//		
//		if(null ==msicValidUptoStr || msicValidUptoStr.isEmpty())
//			msicValidUptoStr = format.format(new Date());
		
		Date msicValidUpto=null;
		Date dlValidUpto=null;
		
		try {
			if(null != dlValidUptoStr && !dlValidUptoStr.isEmpty()) {
				dlValidUpto = format.parse(dlValidUptoStr);
				dlValidUpto = calendarUtil.getConvertedDate(dlValidUpto, 23,59,59);
			}
			
			if(null != msicValidUptoStr && !msicValidUptoStr.isEmpty()) {
				msicValidUpto = format.parse(msicValidUptoStr);
				msicValidUpto = calendarUtil.getConvertedDate(msicValidUpto, 23,59,59);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == sortDir || sortDir.isEmpty()) {
			sortDir = ApplicationConstants.ASC;
		}
		if (null == sortField || sortField.isEmpty()) {
			sortField = ApplicationConstants.ID;
		}
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Page<Driver> page = getPaginatedData(dlNo, name, dlValidUpto, msicNo, msicValidUpto, pageno, sort);
		List<Driver> accessLevelList = page.getContent();

		sortDir = (ApplicationConstants.ASC.equalsIgnoreCase(sortDir)) ? ApplicationConstants.DESC : ApplicationConstants.ASC;
		PaginationDto<Driver> dtoList = new PaginationDto<Driver>(accessLevelList, page.getTotalPages(),
				page.getNumber() + NumberConstants.ONE, page.getSize(), page.getTotalElements(), page.getTotalElements(), sortDir,
				ApplicationConstants.SUCCESS, ApplicationConstants.MSG_TYPE_S);
		return dtoList;
	}


	private Page<Driver> getPaginatedData(String dlNo, String name, Date dlValidUpto, String msicNo, Date msicValidUpto,  int pageno, Sort sort) {
		
		Pageable pageable = PageRequest.of(pageno - NumberConstants.ONE, NumberConstants.TEN, sort);

		Specification<Driver> dlSpc = generalSpecificationDriver.stringSpecification(dlNo, "dlNo");
		Specification<Driver> holderSpc = generalSpecificationDriver.stringSpecification(name, "nameOfHolder");
		Specification<Driver> dlValidUptoSpc = generalSpecificationDriver.lessThanOrEqualToSpecification(dlValidUpto, "dlValidTill");
		Specification<Driver> msciNoSpc = generalSpecificationDriver.stringSpecification(msicNo, "msicNo");
		Specification<Driver> msciValidUptoSpc = generalSpecificationDriver.lessThanOrEqualToSpecification(msicValidUpto, "msicValidTill");
		
		Page<Driver> page = driverRepository.findAll(dlSpc.and(holderSpc).and(dlValidUptoSpc).and(msciNoSpc).and(msciValidUptoSpc), pageable);
		
		return page;
	}
}

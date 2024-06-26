package com.eikona.tech.service.impl;

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
import com.eikona.tech.entity.Container;
import com.eikona.tech.repository.ContainerRepository;
import com.eikona.tech.service.ContainerService;
import com.eikona.tech.util.GeneralSpecificationUtil;

@Service
public class ContainerServiceImpl implements ContainerService{
	
	@Autowired
    private ContainerRepository containerRepository;
	
	@Autowired
    private GeneralSpecificationUtil<Container> generalSpecification;

    @Override
    public List <Container> getAll() {
        return (List<Container>) containerRepository.findAll();
    }

    @Override
    public void save(Container container) {
        this.containerRepository.save(container);
    }

    @Override
    public Container getById(long id) {
        Optional<Container> optional = containerRepository.findById(id);
        Container container = null;
        if (optional.isPresent()) {
        	container = optional.get();
        } else {
            throw new RuntimeException("Department not found for id :"+ id);
        }
        return container;
    }
    
    @Override
	public void deleteById(long id) {
    	Optional<Container> optional = containerRepository.findById(id);
    	Container container = null;
        if (optional.isPresent()) {
        	container = optional.get();
        } else {
            throw new RuntimeException("Department not found for id :"+ id);
        }
        this.containerRepository.save(container);
	}

	@Override
	public PaginationDto<Container> searchByField(String containerNo,String boeNo,String operator,String type, int pageno, String sortField, String sortDir) {
		if (null == sortDir || sortDir.isEmpty()) {
			sortDir = ApplicationConstants.ASC;
		}
		if (null == sortField || sortField.isEmpty()) {
			sortField = ApplicationConstants.ID;
		}
		Page<Container> page = getContainerPage(containerNo,boeNo,operator,type, pageno, sortField, sortDir);
        List<Container> containerList =  page.getContent();
		
		sortDir = (ApplicationConstants.ASC.equalsIgnoreCase(sortDir))?ApplicationConstants.DESC:ApplicationConstants.ASC;
		PaginationDto<Container> dtoList = new PaginationDto<Container>(containerList, page.getTotalPages(),
				page.getNumber() + NumberConstants.ONE, page.getSize(), page.getTotalElements(), page.getTotalElements(), sortDir, ApplicationConstants.SUCCESS, ApplicationConstants.MSG_TYPE_S);
		return dtoList;
	}

	private Page<Container> getContainerPage(String containerNo,String boeNo,String operator,String type, int pageno, String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageno - NumberConstants.ONE, NumberConstants.TEN, sort);
		
		Specification<Container> containerNoSpc = generalSpecification.stringSpecification(containerNo, "containerNo");
		Specification<Container> boeNoSpc = generalSpecification.stringSpecification(boeNo, "boeNo");
		Specification<Container> operatorSpc = generalSpecification.stringSpecification(operator, "operator");
		Specification<Container> typeSpc = generalSpecification.stringSpecification(type, "type");
		
    	Page<Container> page = containerRepository.findAll(containerNoSpc.and(boeNoSpc).and(operatorSpc).and(typeSpc),pageable);
		return page;
	}

	@Override
	public List<Container> getAllByConstructor() {
		
		return (List<Container>) containerRepository.findAll();
	}

}

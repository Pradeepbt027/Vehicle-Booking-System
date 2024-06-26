package com.eikona.tech.service;

import java.util.List;

import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.entity.Container;

public interface ContainerService {
	
	/**
	 * Returns all container List, which are isDeleted false.
	 * @param
	 */
	List<Container> getAll();
	/**
	 * This function saves the container in database according to the respective object.  
	 * @param 
	 */
	void save(Container container);
	/**
	 * This function retrieves the container from database according to the respective id.  
	 * @param
	 */
	Container getById(long id);
	/**
	 * This function deletes the container from database according to the respective id.  
	 * @param
	 */
	void deleteById(long id);
	

	PaginationDto<Container> searchByField(String containerNo,String boeNo,String operator,String type,int pageno, String sortField, String sortDir);
	
	List<Container> getAllByConstructor();

}

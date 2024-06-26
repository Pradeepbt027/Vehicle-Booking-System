package com.eikona.tech.repository;


import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.eikona.tech.entity.Privilege;



@Repository
public interface PrivilegeRepository extends DataTablesRepository<Privilege, Long> {

	List<Privilege> findAllByIsDeletedFalse();
	

}

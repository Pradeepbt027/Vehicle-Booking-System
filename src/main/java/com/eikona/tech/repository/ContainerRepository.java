package com.eikona.tech.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.eikona.tech.entity.Container;

@Repository
public interface ContainerRepository extends DataTablesRepository<Container, Long> {

}

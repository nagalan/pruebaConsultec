package com.consultec.gestioncliente.dao;

import java.util.List;

import com.consultec.gestioncliente.domainobjects.Office;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfficeDAO extends CrudRepository<Office, Long> {

    public List<Office> findByname(String name);

}
package com.consultec.gestioncliente.dao;

import java.util.List;

import com.consultec.gestioncliente.domainobjects.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long> {

    public List<Customer> findByNameOrLastNameOrEmailOrAddressOrStatusOrOffice_id(String name, String lastName, String email, String address, String status, Long office);

}
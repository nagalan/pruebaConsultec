
package com.consultec.gestioncliente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.consultec.gestioncliente.dao.CustomerDAO;
import com.consultec.gestioncliente.domainobjects.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
@Service
public class CustomerService {
 
    @Autowired
    private CustomerDAO repository;
 
    public List<Customer> findAll() {
        List<Customer> lista= new ArrayList<>();
        for (Customer var : repository.findAll()) {
            lista.add(var);
        }
        return lista;
    }

    public Customer save(Customer newCustomer) {
        return repository.save(newCustomer);
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Customer> findByQCustomers(String name, String lastName, String email, String address, String status, Long office){
        return repository.findByNameOrLastNameOrEmailOrAddressOrStatusOrOffice_id(name, lastName, email, address, status, office);
    }

}
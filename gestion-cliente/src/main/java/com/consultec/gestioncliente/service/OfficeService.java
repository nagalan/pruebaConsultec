
package com.consultec.gestioncliente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.consultec.gestioncliente.dao.OfficeDAO;
import com.consultec.gestioncliente.domainobjects.Office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
@Service
public class OfficeService {
 
    @Autowired
    private OfficeDAO repository;
 
    public List<Office> findAll() {
        List<Office> lista= new ArrayList<>();
        for (Office var : repository.findAll()) {
            lista.add(var);
        }
        return lista;
    }

    public Office save(Office newOffice) {
        return repository.save(newOffice);
    }

    public Optional<Office> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Office> findByQOffices(String name){
        return repository.findByname(name);
    }

}
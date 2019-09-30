// Controlador Rest del Microservicio.
package com.consultec.gestioncliente.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.consultec.gestioncliente.domainobjects.Customer;
import com.consultec.gestioncliente.domainobjects.Office;
import com.consultec.gestioncliente.dto.ResponseModel;
import com.consultec.gestioncliente.service.CustomerService;
import com.consultec.gestioncliente.service.OfficeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Validated
public class RestControllerApi {
    private Logger logger = LoggerFactory.getLogger(RestControllerApi.class);

    @Autowired
    private OfficeService officeService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/offices")
    List<Office> findAllOffices() {
        return officeService.findAll();
    }

    @PostMapping("/offices")
    ResponseEntity<ResponseModel> newOffice(@Valid @RequestBody Office newOffice) {
        ResponseModel response = new ResponseModel();
        try {
            response.responsePorBien(officeService.save(newOffice));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.responsePorMal(newOffice, "500", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity<ResponseModel> findOfficeById(@PathVariable Long id) {
        ResponseModel response = new ResponseModel();
        Optional<Office> stock = officeService.findById(id);
        if (!stock.isPresent()) {
            String mensaje = "Oficina con Id[" + id + "] no existe";
            logger.error(mensaje);
            response.responsePorMal(id, "500", mensaje);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        response.responsePorBien(stock.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/offices/query")
    public ResponseEntity<ResponseModel> findOfficeByQuery(
            @RequestParam(required = false) String name) {
        ResponseModel response = new ResponseModel();
        List<Office> list = officeService.findByQOffices(name);
        response.responsePorBien(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/offices/{id}")
    ResponseEntity<ResponseModel> updateOffice(@PathVariable Long id, @Valid @RequestBody Office newOffice) {
        ResponseModel response = new ResponseModel();
        if (!officeService.findById(id).isPresent()) {
            String mensaje = "Oficina con Id[" + id + "] no existe";
            logger.error(mensaje);
            response.responsePorMal(newOffice, "500", mensaje);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.responsePorBien(officeService.save(newOffice));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/offices/{id}")
    public ResponseEntity<ResponseModel> deleteOffice(@PathVariable Long id) {
        ResponseModel response = new ResponseModel();
        if (!officeService.findById(id).isPresent()) {
            String mensaje = "Oficina con Id[" + id + "] no existe";
            logger.error(mensaje);
            response.responsePorMal(id, "500", mensaje);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        officeService.deleteById(id);
        response.responsePorBien(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/customers")
    List<Customer> findAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping("/customers")
    ResponseEntity<ResponseModel> newCustomer(@Valid @RequestBody Customer newCustomer) {
        ResponseModel response = new ResponseModel();
        try {
            response.responsePorBien(customerService.save(newCustomer));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.responsePorMal(newCustomer, "500", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<ResponseModel> findCustomerById(@PathVariable Long id) {
        ResponseModel response = new ResponseModel();
        Optional<Customer> stock = customerService.findById(id);
        if (!stock.isPresent()) {
            String mensaje = "Cliente con Id[" + id + "] no existe";
            logger.error(mensaje);
            response.responsePorMal(id, "500", mensaje);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        response.responsePorBien(stock.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/customers/query")
    public ResponseEntity<ResponseModel> findCustomerByQuery(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName, 
            @RequestParam(required = false) String email, 
            @RequestParam(required = false) String address, 
            @RequestParam(required = false) String status, 
            @RequestParam(required = false) Long office) {
        ResponseModel response = new ResponseModel();
        List<Customer> list = customerService.findByQCustomers(name, lastName, email, address, status, office);
        response.responsePorBien(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/customers/{id}")
    ResponseEntity<ResponseModel> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer newCustomer) {
        ResponseModel response = new ResponseModel();
        if (!customerService.findById(id).isPresent()) {
            String mensaje = "Cliente con Id[" + id + "] no existe";
            logger.error(mensaje);
            response.responsePorMal(newCustomer, "500", mensaje);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.responsePorBien(customerService.save(newCustomer));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<ResponseModel> deleteCustomer(@PathVariable Long id) {
        ResponseModel response = new ResponseModel();
        if (!customerService.findById(id).isPresent()) {
            String mensaje = "Cliente con Id[" + id + "] no existe";
            logger.error(mensaje);
            response.responsePorMal(id, "500", mensaje);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        customerService.deleteById(id);
        response.responsePorBien(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}


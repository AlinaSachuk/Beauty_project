package com.beauty_project.controller;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.UpdateCustomerDto;
import com.beauty_project.service.CustomerService;
import com.beauty_project.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final VisitService visitService;

    @Autowired
    public CustomerController(CustomerService customerService, VisitService visitService) {
        this.customerService = customerService;
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> list = customerService.getAllCustomers();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getVisits/{id}")
    public ResponseEntity<List<Visit>> getAllVisitsForSingleCustomer(@PathVariable int id) {
        List<Visit> list = visitService.getAllVisitsForSingleCustomer(id);
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody RegistrationCustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateCustomer(@RequestBody UpdateCustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
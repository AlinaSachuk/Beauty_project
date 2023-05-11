package com.beauty_project.controller;

import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.CustomerUpdateRequestDto;
import com.beauty_project.domain.response.CustomerResponseDto;
import com.beauty_project.domain.response.VisitResponseDto;
import com.beauty_project.service.CustomerService;
import com.beauty_project.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getVisits/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<VisitResponseDto> getAllVisitsForSingleCustomer(@PathVariable int id) {
        return visitService.getAllVisitsForSingleCustomer(id);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@RequestBody RegistrationCustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @PutMapping("/updateInfo")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto updateCustomer(@RequestBody CustomerUpdateRequestDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
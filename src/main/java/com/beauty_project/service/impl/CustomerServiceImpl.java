package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.dto.RegistrationCustomerDto;
import com.beauty_project.domain.dto.UpdateCustomerDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public ArrayList<Customer> getAllCustomers() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer createCustomer(RegistrationCustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setTelephoneNumber(customerDto.getTelephoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setInstagramAccount(customerDto.getInstagramAccount());
        customer.setPassword(customerDto.getPassword());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(UpdateCustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(()-> new NotFoundException("Customer by id=" + customerDto.getId() + " not found"));
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setTelephoneNumber(customerDto.getTelephoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setInstagramAccount(customerDto.getInstagramAccount());
        customer.setPassword(customerDto.getPassword());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
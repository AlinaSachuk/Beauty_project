package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public ArrayList<Customer> getAllCustomers() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        customerRepository.findById(customer.getId()).orElseThrow(() -> new NotFoundException("Customer with id=" + customer.getId() + " not found"));
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id=" + id + " not found"));
        customerRepository.deleteById(id);
    }
}
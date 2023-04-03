package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById (int id){
        return customerRepository.getCustomerById(id);
    }
}

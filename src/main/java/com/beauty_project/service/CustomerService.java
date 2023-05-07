package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.CustomerUpdateRequestDto;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();

    Customer createCustomer(RegistrationCustomerDto customerDto);

    Customer updateCustomer(CustomerUpdateRequestDto customerDto);

    void deleteCustomer(int id);
}
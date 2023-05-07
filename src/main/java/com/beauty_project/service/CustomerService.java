package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.UpdateCustomerDto;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();

    Customer createCustomer(RegistrationCustomerDto customerDto);

    Customer updateCustomer(UpdateCustomerDto customerDto);

    void deleteCustomer(int id);
}
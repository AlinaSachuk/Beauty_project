package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.dto.RegistrationCustomerDto;
import com.beauty_project.domain.dto.UpdateCustomerDto;

import java.util.ArrayList;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> getCustomerById(int id);

    ArrayList<Customer> getAllCustomers();

    Customer createCustomer(RegistrationCustomerDto customerDto);

    Customer updateCustomer(UpdateCustomerDto customerDto);

    void deleteCustomer(int id);
}
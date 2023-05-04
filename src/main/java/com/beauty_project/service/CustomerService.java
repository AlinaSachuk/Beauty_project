package com.beauty_project.service;

import com.beauty_project.domain.Customer;

import java.util.ArrayList;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> getCustomerById(int id);

    ArrayList<Customer> getAllCustomers();

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
package com.beauty_project.service;

import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.CustomerUpdateRequestDto;
import com.beauty_project.domain.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto getCustomerById(int id);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto createCustomer(RegistrationCustomerDto customerDto);

    CustomerResponseDto updateCustomer(CustomerUpdateRequestDto customerDto);

    void deleteCustomer(int id);
}
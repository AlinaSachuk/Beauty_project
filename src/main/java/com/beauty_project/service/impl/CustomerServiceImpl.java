package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.CustomerUpdateRequestDto;
import com.beauty_project.domain.response.CustomerResponseDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final String CUSTOMER_ROLE = "USER";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerResponseDto getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found"));
        return new CustomerResponseDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getTelephoneNumber(),
                customer.getEmail(),
                customer.getInstagramAccount()
        );
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerResponseDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getTelephoneNumber(),
                customer.getEmail(),
                customer.getInstagramAccount()
        )).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto createCustomer(RegistrationCustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setTelephoneNumber(customerDto.getTelephoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setInstagramAccount(customerDto.getInstagramAccount());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setRole(CUSTOMER_ROLE);
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDto(
                savedCustomer.getId(),
                savedCustomer.getCustomerName(),
                savedCustomer.getTelephoneNumber(),
                savedCustomer.getEmail(),
                savedCustomer.getInstagramAccount()
        );
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerUpdateRequestDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new NotFoundException("Customer by id=" + customerDto.getId() + " not found"));
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setTelephoneNumber(customerDto.getTelephoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setInstagramAccount(customerDto.getInstagramAccount());
        customer.setPassword(customerDto.getPassword());
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDto(
                savedCustomer.getId(),
                savedCustomer.getCustomerName(),
                savedCustomer.getTelephoneNumber(),
                savedCustomer.getEmail(),
                savedCustomer.getInstagramAccount()
        );
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
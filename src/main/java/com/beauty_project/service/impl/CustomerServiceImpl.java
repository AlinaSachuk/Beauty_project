package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.request.CustomerStatusUpdateRequestDto;
import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.CustomerUpdateRequestDto;
import com.beauty_project.domain.response.CustomerNewStatusResponseDto;
import com.beauty_project.domain.response.CustomerResponseDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final String CUSTOMER_ROLE = "CUSTOMER";
    private final String CUSTOMER_STATUS = "Null";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerResponseDto getCustomerById(int id) {
        String authenticationLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer securityCustomer = customerRepository.findCustomerByTelephoneNumber(authenticationLogin)
                .orElseThrow(() -> new NotFoundException("Customer with phone number=" + authenticationLogin
                        + " not found."));
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found"));
        if (authenticationLogin.equals(customer.getTelephoneNumber()) | (securityCustomer.getRole()).equals("ADMIN")) {
            return new CustomerResponseDto(
                    customer.getId(),
                    customer.getCustomerName(),
                    customer.getTelephoneNumber(),
                    customer.getEmail(),
                    customer.getInstagramAccount(),
                    customer.getStatus()
            );
        } else {
            throw new AuthorizationServiceException("Customer with login=" + authenticationLogin
                    + "trying to get forbidden information.");
        }
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerResponseDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getTelephoneNumber(),
                customer.getEmail(),
                customer.getInstagramAccount(),
                customer.getStatus()
        )).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto createCustomer(RegistrationCustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setTelephoneNumber(customerDto.getTelephoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setInstagramAccount(customerDto.getInstagramAccount());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setStatus(CUSTOMER_STATUS);
        customer.setRole(CUSTOMER_ROLE);
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDto(
                savedCustomer.getId(),
                savedCustomer.getCustomerName(),
                savedCustomer.getTelephoneNumber(),
                savedCustomer.getEmail(),
                savedCustomer.getInstagramAccount(),
                savedCustomer.getStatus()
        );
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerUpdateRequestDto customerDto) {
        String authenticationLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new NotFoundException("Customer by id=" + customerDto.getId() + " not found"));
        if (authenticationLogin.equals(customer.getTelephoneNumber())) {
            customer.setCustomerName(customerDto.getCustomerName());
            customer.setTelephoneNumber(customerDto.getTelephoneNumber());
            customer.setEmail(customerDto.getEmail());
            customer.setInstagramAccount(customerDto.getInstagramAccount());
            customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        } else {
            throw new AuthorizationServiceException("Customer with login=" + authenticationLogin
                    + " trying to put forbidden information.");
        }
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDto(
                savedCustomer.getId(),
                savedCustomer.getCustomerName(),
                savedCustomer.getTelephoneNumber(),
                savedCustomer.getEmail(),
                savedCustomer.getInstagramAccount(),
                savedCustomer.getStatus()
        );
    }

    @Override
    public CustomerNewStatusResponseDto updateCustomersStatus(CustomerStatusUpdateRequestDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new NotFoundException("Customer by id=" + customerDto.getId() + " not found"));
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setStatus(customerDto.getStatus());
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerNewStatusResponseDto(
                savedCustomer.getId(),
                savedCustomer.getCustomerName(),
                savedCustomer.getStatus()
        );
    }

    @Override
    public void deleteCustomer(int id) {
        String authenticationLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer securityCustomer = customerRepository.findCustomerByTelephoneNumber(authenticationLogin)
                .orElseThrow(() -> new NotFoundException("Customer with phone number=" + authenticationLogin
                        + " not found."));
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found"));
        if (authenticationLogin.equals(customer.getTelephoneNumber()) | (securityCustomer.getRole()).equals("ADMIN")) {
            customerRepository.deleteById(id);
        } else {
            throw new AuthorizationServiceException("Customer with login=" + authenticationLogin
                    + " trying to put forbidden information.");
        }
    }
}
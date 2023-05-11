package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.request.RegistrationAdminDto;
import com.beauty_project.domain.response.AdministratorResponseDto;
import com.beauty_project.domain.response.CustomerResponseDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final CustomerRepository customerRepository;
    private final String ADMIN_ROLE = "ADMIN";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdministratorServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdministratorResponseDto createAdmin(RegistrationAdminDto adminDto) {
        Customer customer = new Customer();
        customer.setCustomerName(adminDto.getName());
        customer.setBirthDate(adminDto.getBirthDate());
        customer.setTelephoneNumber(adminDto.getTelephoneNumber());
        customer.setEmail(adminDto.getEmail());
        customer.setInstagramAccount(adminDto.getInstagramAccount());
        customer.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        customer.setRole(ADMIN_ROLE);
        Customer savedCustomer = customerRepository.save(customer);
        return new AdministratorResponseDto(
                savedCustomer.getId(),
                savedCustomer.getCustomerName()
        );
    }
}
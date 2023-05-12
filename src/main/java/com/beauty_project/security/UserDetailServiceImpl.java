package com.beauty_project.security;

import com.beauty_project.domain.Customer;
import com.beauty_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Autowired
    public UserDetailServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByTelephoneNumber(username)
                .orElseThrow(() -> new NotFoundException("Customer with telephone number=" + username + "not found."));
        UserDetails securityUser = org.springframework.security.core.userdetails.User
                .builder()
                .username(customer.getTelephoneNumber())
                .password(customer.getPassword())
                .roles(customer.getRole())
                .build();
        return securityUser;
    }
}
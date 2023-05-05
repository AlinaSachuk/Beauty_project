package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    private int id;
    private Customer customer;
    private List<Customer> allCustomers;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(customerRepository).findById(id);
        assertThrows(NotFoundException.class, () -> customerService.getCustomerById(id));
    }

    @Test
    public void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(allCustomers);
        List<Customer> returnedCustomers = customerService.getAllCustomers();
        verify(customerRepository).findAll();
        assertEquals(allCustomers, returnedCustomers);
    }

    @Test
    public void testDeleteCustomerByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> customerService.deleteCustomer(id));
    }
}
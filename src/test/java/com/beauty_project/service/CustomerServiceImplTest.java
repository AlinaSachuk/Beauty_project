package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.Customer;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = Utils.createCustomer(1, "Bob", Date.valueOf("2000-09-18"), "80449871254"
                , "bob@gmail.com", "bob1809", "SILVER", "bob1809");
        Customer customer2 = Utils.createCustomer(2, "Bob", Date.valueOf("2000-09-18"), "80449871254"
                , "bob@gmail.com", "bob1809", "SILVER", "bob1809");
        List<Customer> returnedCustomers = new ArrayList<>();
        returnedCustomers.add(customer1);
        returnedCustomers.add(customer2);
        when(customerRepository.findAll()).thenReturn(returnedCustomers);
        List<Customer> actual = customerRepository.findAll();
        verify(customerRepository, times(1)).findAll();
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    public void testDeleteCustomerById() {
        customerService.deleteCustomer(1);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCustomerByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> customerService.deleteCustomer(1));
    }
}
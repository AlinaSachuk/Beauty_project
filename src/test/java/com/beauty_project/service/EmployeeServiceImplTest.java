package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.Employee;
import com.beauty_project.repository.EmployeeRepository;
import com.beauty_project.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(employeeRepository).findById(1);
        assertThrows(NotFoundException.class, () -> employeeService.getEmployeeById(1));
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = Utils.createEmployee(1, "Anna", "doctor", "BSMU", "since 2015");
        Employee employee2 = Utils.createEmployee(2, "Anna", "doctor", "BSMU", "since 2015");
        List<Employee> returnedEmployees = new ArrayList<>();
        returnedEmployees.add(employee1);
        returnedEmployees.add(employee2);
        when(employeeRepository.findAll()).thenReturn(returnedEmployees);
        List<Employee> actual = employeeRepository.findAll();
        verify(employeeRepository, times(1)).findAll();
        assertEquals(2, actual.size());
    }

    @Test
    public void testDeleteEmployeeById() {
        employeeService.deleteEmployee(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteEmployeeByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(employeeRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> employeeService.deleteEmployee(1));
    }
}
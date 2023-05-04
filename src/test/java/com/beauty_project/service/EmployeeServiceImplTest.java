package com.beauty_project.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private int id;
    private Employee employee;
    private ArrayList<Employee> allEmployees;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(employeeRepository).findById(id);
        assertThrows(NotFoundException.class, () -> employeeService.getEmployeeById(id));
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(allEmployees);
        ArrayList<Employee> returnedEmployees = employeeService.getAllEmployees();
        verify(employeeRepository).findAll();
        assertEquals(allEmployees, returnedEmployees);
    }

    @Test
    public void testDeleteEmployeeByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(employeeRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> employeeService.deleteEmployee(id));
    }
}

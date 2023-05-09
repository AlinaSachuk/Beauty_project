package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.Employee;
import com.beauty_project.domain.request.EmployeeRequestDto;
import com.beauty_project.domain.response.EmployeeResponseDto;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    public void testGetById() {
        var employee = Utils.createEmployee(1, "Anna", "doctor"
                , "BSMU", "since 2015");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        EmployeeResponseDto actual = employeeService.getEmployeeById(1);
        assertNotNull(actual);
        assertEquals(new EmployeeResponseDto(1, "Anna", "doctor"
                , "BSMU", "since 2015"), actual);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = Utils.createEmployee(1, "Anna", "doctor"
                , "BSMU", "since 2015");
        Employee employee2 = Utils.createEmployee(2, "Anna", "doctor"
                , "BSMU", "since 2015");
        List<Employee> returnedEmployees = new ArrayList<>();
        returnedEmployees.add(employee1);
        returnedEmployees.add(employee2);
        when(employeeRepository.findAll()).thenReturn(returnedEmployees);
        List<Employee> actual = employeeRepository.findAll();
        verify(employeeRepository, times(1)).findAll();
        assertEquals(2, actual.size());
    }

    @Test
    public void testSave() {
        var employee = Utils.createEmployee(1, "Anna", "doctor"
                , "BSMU", "since 2015");
        when(employeeRepository.save(any())).thenReturn(employee);
        EmployeeResponseDto actual = employeeService.createEmployee(new EmployeeRequestDto(
                1, "Anna", "doctor", "BSMU", "since 2015"));
        assertNotNull(actual);
        assertEquals(new EmployeeResponseDto(1, "Anna", "doctor"
                , "BSMU", "since 2015"), actual);
    }

    @Test
    public void testUpdate() {
        var savedEmployee = Utils.createEmployee(1, "Anna", "doctor"
                , "BSMU", "since 2015");
        var updatedEmployee = Utils.createEmployee(1, "Hanna", "doctor"
                , "BSMU", "since 2015");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(savedEmployee));
        when(employeeRepository.save(any())).thenReturn(updatedEmployee);
        EmployeeResponseDto actual = employeeService.updateEmployee(new EmployeeRequestDto(
                1, "Anna", "doctor", "BSMU", "since 2015"));
        assertNotNull(actual);
        assertEquals(new EmployeeResponseDto(1, "Hanna", "doctor"
                , "BSMU", "since 2015"), actual);
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
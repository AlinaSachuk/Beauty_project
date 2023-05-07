package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.domain.request.EmployeeRequestDto;
import com.beauty_project.domain.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto getEmployeeById(int id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeDto);

    EmployeeResponseDto updateEmployee(EmployeeRequestDto employeeDto);

    void deleteEmployee(int id);
}

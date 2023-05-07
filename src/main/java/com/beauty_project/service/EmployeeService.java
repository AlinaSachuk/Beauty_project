package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.domain.request.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee createEmployee(EmployeeRequestDto employeeDto);

    Employee updateEmployee(EmployeeRequestDto employeeDto);

    void deleteEmployee(int id);
}

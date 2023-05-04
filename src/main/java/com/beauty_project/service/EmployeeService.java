package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.domain.dto.CreateUpdateEmployeeDto;

import java.util.ArrayList;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployeeById(int id);

    ArrayList<Employee> getAllEmployees();

    Employee createEmployee(CreateUpdateEmployeeDto employeeDto);

    Employee updateEmployee(CreateUpdateEmployeeDto employeeDto);

    void deleteEmployee(int id);
}

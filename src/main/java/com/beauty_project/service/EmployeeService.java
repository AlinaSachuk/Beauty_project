package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.domain.dto.CreateUpdateEmployeeDto;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee createEmployee(CreateUpdateEmployeeDto employeeDto);

    Employee updateEmployee(CreateUpdateEmployeeDto employeeDto);

    void deleteEmployee(int id);
}

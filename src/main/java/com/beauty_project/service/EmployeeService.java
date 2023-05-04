package com.beauty_project.service;

import com.beauty_project.domain.Employee;

import java.util.ArrayList;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployeeById(int id);

    ArrayList<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(int id);
}

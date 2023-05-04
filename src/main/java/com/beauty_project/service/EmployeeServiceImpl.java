package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public ArrayList<Employee> getAllEmployees() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
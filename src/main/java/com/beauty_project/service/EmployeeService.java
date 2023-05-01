package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
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
        employeeRepository.findById(employee.getId()).orElseThrow(() -> new NotFoundException("Employee with id=" + employee.getId() + " not found"));
        return employeeRepository.saveAndFlush(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee with id=" + id + " not found"));
        employeeRepository.deleteById(id);
    }
}
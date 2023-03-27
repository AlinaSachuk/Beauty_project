package com.beauty_project.service;

import com.beauty_project.domain.Employee;
import com.beauty_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(int id) {
     //   return employeeRepository.getEmployeeById(id);
        return new Employee();
    }
}

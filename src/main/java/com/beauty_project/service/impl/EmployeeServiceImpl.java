package com.beauty_project.service.impl;

import com.beauty_project.domain.Employee;
import com.beauty_project.domain.request.CreateUpdateEmployeeDto;
import com.beauty_project.repository.EmployeeRepository;
import com.beauty_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employee by id=" + id + " not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(CreateUpdateEmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeName(employee.getEmployeeName());
        employee.setPosition(employee.getPosition());
        employee.setEducation(employee.getEducation());
        employee.setWorkExperience(employee.getWorkExperience());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(CreateUpdateEmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(()-> new NotFoundException("Employee by id=" + employeeDto.getId() + " not found"));
        employee.setEmployeeName(employee.getEmployeeName());
        employee.setPosition(employee.getPosition());
        employee.setEducation(employee.getEducation());
        employee.setWorkExperience(employee.getWorkExperience());
        return employeeRepository.saveAndFlush(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
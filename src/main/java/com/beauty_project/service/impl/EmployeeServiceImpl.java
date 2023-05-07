package com.beauty_project.service.impl;

import com.beauty_project.domain.Employee;
import com.beauty_project.domain.request.EmployeeRequestDto;
import com.beauty_project.domain.response.EmployeeResponseDto;
import com.beauty_project.repository.EmployeeRepository;
import com.beauty_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee by id=" + id + " not found"));
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getEmployeeName(),
                employee.getPosition(),
                employee.getEducation(),
                employee.getWorkExperience()
        );
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> new EmployeeResponseDto(
                employee.getId(),
                employee.getEmployeeName(),
                employee.getPosition(),
                employee.getEducation(),
                employee.getWorkExperience()
        )).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeName(employee.getEmployeeName());
        employee.setPosition(employee.getPosition());
        employee.setEducation(employee.getEducation());
        employee.setWorkExperience(employee.getWorkExperience());
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeResponseDto(
                savedEmployee.getId(),
                savedEmployee.getEmployeeName(),
                savedEmployee.getPosition(),
                savedEmployee.getEducation(),
                savedEmployee.getWorkExperience()
        );
    }

    @Override
    public EmployeeResponseDto updateEmployee(EmployeeRequestDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new NotFoundException("Employee by id=" + employeeDto.getId() + " not found"));
        employee.setEmployeeName(employee.getEmployeeName());
        employee.setPosition(employee.getPosition());
        employee.setEducation(employee.getEducation());
        employee.setWorkExperience(employee.getWorkExperience());
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeResponseDto(
                savedEmployee.getId(),
                savedEmployee.getEmployeeName(),
                savedEmployee.getPosition(),
                savedEmployee.getEducation(),
                savedEmployee.getWorkExperience()
        );
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
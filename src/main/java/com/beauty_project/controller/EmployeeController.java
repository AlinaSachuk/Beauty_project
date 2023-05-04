package com.beauty_project.controller;

import com.beauty_project.domain.Employee;
import com.beauty_project.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isEmpty()) {
            log.warn("Employee with id=" + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Employee>> getAllEmployees() {
        ArrayList<Employee> list = employeeService.getAllEmployees();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
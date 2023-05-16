package com.beauty_project.controller;

import com.beauty_project.domain.request.EmployeeRequestDto;
import com.beauty_project.domain.response.EmployeeResponseDto;
import com.beauty_project.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Don`t have authorization"),
        @ApiResponse(responseCode = "403", description = "Don`t have authority")
})
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Finding employee by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found by id successfully."),
            @ApiResponse(responseCode = "404", description = "Employee by entered id not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDto getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    @Operation(description = "Finding all employees.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All employees found successfully"),
            @ApiResponse(responseCode = "404", description = "Employees not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    @Operation(description = "Creating employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully."),
            @ApiResponse(responseCode = "400", description = "Something wrong: employee not created.")
    })
    @ApiResponse(responseCode = "201", description = "Employee created successfully.")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDto createEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @PutMapping
    @Operation(description = "Updating information about employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee`s information updated successfully."),
            @ApiResponse(responseCode = "400", description = "Something wrong: employee not created."),
            @ApiResponse(responseCode = "404", description = "Requested employee not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDto updateEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleting employee by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted by id successfully."),
            @ApiResponse(responseCode = "404", description = "Requested employee not found.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }
}
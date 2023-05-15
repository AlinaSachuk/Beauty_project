package com.beauty_project.controller;

import com.beauty_project.domain.request.CustomerStatusUpdateRequestDto;
import com.beauty_project.domain.request.RegistrationCustomerDto;
import com.beauty_project.domain.request.CustomerUpdateRequestDto;
import com.beauty_project.domain.response.CustomerNewStatusResponseDto;
import com.beauty_project.domain.response.CustomerResponseDto;
import com.beauty_project.domain.response.VisitResponseDto;
import com.beauty_project.service.CustomerService;
import com.beauty_project.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final VisitService visitService;

    @Autowired
    public CustomerController(CustomerService customerService, VisitService visitService) {
        this.customerService = customerService;
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Finding customer by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found by id successfully."),
            @ApiResponse(responseCode = "404", description = "Customer by entered id not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/getAll")
    @Operation(description = "Finding all customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All customers found successfully"),
            @ApiResponse(responseCode = "404", description = "Customers not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getVisits/{id}")
    @Operation(description = "Finding all visits by customer`s id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All customer`s visits found successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<VisitResponseDto> getAllVisitsForSingleCustomer(
            @Parameter(description = "This is customer`s id you need to enter to get all visit")
            @PathVariable int id) {
        return visitService.getAllVisitsForSingleCustomer(id);
    }

    @PostMapping("/registration")
    @Operation(description = "Creating customer")
    @ApiResponse(responseCode = "201", description = "Customer created successfully.")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@RequestBody RegistrationCustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @PutMapping("/updateInfo")
    @Operation(description = "Updating information about customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer`s  information updated successfully."),
            @ApiResponse(responseCode = "404", description = "Requested customer not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto updateCustomer(@RequestBody CustomerUpdateRequestDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @PutMapping("/updateStatus")
    @Operation(description = "Updating customer`s status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer`s  status updated successfully."),
            @ApiResponse(responseCode = "404", description = "Requested customer not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public CustomerNewStatusResponseDto updateCustomersStatus(@RequestBody CustomerStatusUpdateRequestDto customerDto) {
        return customerService.updateCustomersStatus(customerDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleting customer by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted by id successfully."),
            @ApiResponse(responseCode = "404", description = "Requested customer not found.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
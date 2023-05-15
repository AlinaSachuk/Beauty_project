package com.beauty_project.controller;

import com.beauty_project.domain.request.VisitRequestDto;
import com.beauty_project.domain.response.VisitResponseDto;
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
        @ApiResponse(responseCode = "403", description = "Don`t have authority"),
        @ApiResponse(responseCode = "404", description = "Resource not found")
})
@RequestMapping("/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Finding visit by id.")
    @ApiResponse(responseCode = "200", description = "Visit found by id successfully.")
    @ResponseStatus(HttpStatus.OK)
    public VisitResponseDto getVisitById(@PathVariable int id) {
        return visitService.getVisitById(id);
    }

    @GetMapping
    @Operation(description = "Finding all visits.")
    @ApiResponse(responseCode = "200", description = "All visits found successfully.")
    @ResponseStatus(HttpStatus.OK)
    public List<VisitResponseDto> getAllVisits() {
        return visitService.getAllVisits();
    }

    @PostMapping("/{id}")
    @Operation(description = "Creating visit by customer`s id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Visit created successfully."),
            @ApiResponse(responseCode = "400", description = "Incorrect price entered."),
            @ApiResponse(responseCode = "404", description = "Customer not found.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public VisitResponseDto createVisit(@RequestBody VisitRequestDto visitDto
            , @Parameter(description = "This is customer`s id you need to enter to create visit")
                                            @PathVariable int id) {
        return visitService.createVisit(visitDto, id);
    }

    @PutMapping
    @Operation(description = "Updating information about visit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visit`s info updated successfully."),
            @ApiResponse(responseCode = "400", description = "Incorrect price entered.")
    })
    @ResponseStatus(HttpStatus.OK)
    public VisitResponseDto updateVisit(@RequestBody VisitRequestDto visitDto) {
        return visitService.updateVisit(visitDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleting visit by id.")
    @ApiResponse(responseCode = "204", description = "Visit deleted by id successfully.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVisit(@PathVariable int id) {
        visitService.deleteVisit(id);
    }
}
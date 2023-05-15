package com.beauty_project.controller;

import com.beauty_project.domain.request.StatusRequestDto;
import com.beauty_project.domain.response.StatusResponseDto;
import com.beauty_project.service.StatusService;
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
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Finding status by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status found by id successfully."),
            @ApiResponse(responseCode = "404", description = "Status by entered id not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto getStatusById(@PathVariable int id) {
        return statusService.getStatusById(id);
    }

    @GetMapping
    @Operation(description = "Finding all statuses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All statuses found successfully"),
            @ApiResponse(responseCode = "404", description = "Status not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<StatusResponseDto> getAllStatus() {
        return statusService.getAllStatus();
    }

    @PostMapping
    @Operation(description = "Creating status")
    @ApiResponse(responseCode = "201", description = "Status created successfully.")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createStatus(@RequestBody StatusRequestDto statusDto) {
        return statusService.createStatus(statusDto);
    }

    @PutMapping
    @Operation(description = "Updating status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status updated successfully."),
            @ApiResponse(responseCode = "404", description = "Requested status not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateStatus(@RequestBody StatusRequestDto statusDto) {
        return statusService.updateStatus(statusDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleting status by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Visit deleted by id successfully."),
            @ApiResponse(responseCode = "404", description = "Requested status not found.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatus(@PathVariable int id) {
        statusService.deleteStatus(id);
    }
}
package com.beauty_project.controller;

import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.domain.response.AllProceduresResponseDto;
import com.beauty_project.domain.response.ProcedureResponseDto;
import com.beauty_project.service.ProcedureService;
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
@RequestMapping("/procedure")
public class ProcedureController {
    private final ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Finding procedure by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Procedure found by id successfully."),
            @ApiResponse(responseCode = "404", description = "Procedure by entered id not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public ProcedureResponseDto getProcedureById(@PathVariable int id) {
        return procedureService.getProcedureById(id);
    }

    @GetMapping
    @Operation(description = "Finding all procedures.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All procedures found successfully"),
            @ApiResponse(responseCode = "404", description = "Procedures not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<AllProceduresResponseDto> getAllProcedures() {
        return procedureService.getAllProcedures();
    }

    @PostMapping
    @Operation(description = "Creating procedure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Procedure created successfully."),
            @ApiResponse(responseCode = "400", description = "Something wrong: procedure not created.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ProcedureResponseDto createProcedure(@RequestBody ProcedureRequestDto procedureDto) {
        return procedureService.createProcedure(procedureDto);
    }

    @PutMapping
    @Operation(description = "Updating procedure.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Procedure updated successfully."),
            @ApiResponse(responseCode = "400", description = "Something wrong: procedure not created."),
            @ApiResponse(responseCode = "404", description = "Requested procedure not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public ProcedureResponseDto updateProcedure(@RequestBody ProcedureRequestDto procedureDto) {
        return procedureService.updateProcedure(procedureDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleting procedure by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Procedure deleted by id successfully."),
            @ApiResponse(responseCode = "404", description = "Requested procedure not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    public void deleteProcedure(@PathVariable int id) {
        procedureService.deleteProcedure(id);
    }
}
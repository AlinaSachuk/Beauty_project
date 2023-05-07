package com.beauty_project.controller;

import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.domain.response.ProcedureResponseDto;
import com.beauty_project.service.ProcedureService;
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
@RequestMapping("/procedure")
public class ProcedureController {
    private final ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProcedureResponseDto getProcedureById(@PathVariable int id) {
        return procedureService.getProcedureById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProcedureResponseDto> getAllProcedures() {
        return procedureService.getAllProcedures();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcedureResponseDto createProcedure(@RequestBody ProcedureRequestDto procedureDto) {
        return procedureService.createProcedure(procedureDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProcedureResponseDto updateProcedure(@RequestBody ProcedureRequestDto procedureDto) {
        return procedureService.updateProcedure(procedureDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProcedure(@PathVariable int id) {
        procedureService.deleteProcedure(id);
    }
}
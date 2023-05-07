package com.beauty_project.controller;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.request.CreateUpdateProcedureDto;
import com.beauty_project.service.ProcedureService;
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
    public ResponseEntity<Procedure> getProcedureById(@PathVariable int id) {
        Procedure procedure = procedureService.getProcedureById(id);
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Procedure>> getAllProcedures() {
        List<Procedure> procedureList = procedureService.getAllProcedures();
        return new ResponseEntity<>(procedureList, (!procedureList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProcedure(@RequestBody CreateUpdateProcedureDto procedureDto) {
        procedureService.createProcedure(procedureDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateProcedure(@RequestBody CreateUpdateProcedureDto procedureDto) {
        procedureService.updateProcedure(procedureDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProcedure(@PathVariable int id) {
        procedureService.deleteProcedure(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
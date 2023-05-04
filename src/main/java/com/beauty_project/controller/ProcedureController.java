package com.beauty_project.controller;

import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.dto.CreateUpdateProcedureDto;
import com.beauty_project.service.ProcedureService;
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
@RequestMapping("/procedure")
public class ProcedureController {
    private final ProcedureService procedureService;

    private static final Logger log = LoggerFactory.getLogger(ProcedureController.class);

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Procedure>> getProcedureById(@PathVariable int id) {
        Optional<Procedure> procedure = procedureService.getProcedureById(id);
        if (procedure.isEmpty()) {
            log.warn("Procedure with id=" + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Procedure>> getAllProcedures() {
        ArrayList<Procedure> procedureArrayList = procedureService.getAllProcedures();
        return new ResponseEntity<>(procedureArrayList, (!procedureArrayList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
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
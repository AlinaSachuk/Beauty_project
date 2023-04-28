package com.beauty_project.controller;

import com.beauty_project.domain.Procedure;
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

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {
    ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Procedure>> getProcedureById(@PathVariable int id) {
        Optional<Procedure> procedure = procedureService.getProcedureById(id);
        if (procedure.isEmpty()) {
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
    public ResponseEntity<HttpStatus> createProcedure(@RequestBody Procedure procedure) {
        procedureService.createProcedure(procedure);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateProcedure(@RequestBody Procedure procedure) {
        procedureService.updateProcedure(procedure);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProcedure(@PathVariable int id) {
        procedureService.deleteProcedure(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

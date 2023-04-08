package com.beauty_project.controller;

import com.beauty_project.domain.Procedures;
import com.beauty_project.service.ProceduresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/procedure")
public class ProceduresController {
    ProceduresService proceduresService;

    @Autowired
    public ProceduresController(ProceduresService proceduresService) {
        this.proceduresService = proceduresService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Procedures>> getProcedureById(@PathVariable int id) {
        Optional<Procedures> procedure = proceduresService.getProcedureById(id);
        if (procedure.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Procedures>> getAllProcedures() {
        ArrayList<Procedures> proceduresArrayList = proceduresService.getAllProcedures();
        return new ResponseEntity<>(proceduresArrayList, (!proceduresArrayList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProcedure(@RequestBody Procedures procedure) {
        proceduresService.createProcedure(procedure);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateProcedure(@RequestBody Procedures procedure) {
        proceduresService.updateProcedure(procedure);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProcedure(@PathVariable int id) {
        proceduresService.deleteProcedure(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.beauty_project.controller;

import com.beauty_project.domain.Procedures;
import com.beauty_project.service.ProceduresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/procedure")
public class ProceduresController {
    ProceduresService proceduresService;

    @Autowired
    public ProceduresController(ProceduresService proceduresService) {
        this.proceduresService = proceduresService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedures> getProcedureById(@PathVariable int id) {
        Procedures procedure = proceduresService.getProcedureById(id);
        if (procedure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Procedures>> getAllProcedures(){
        ArrayList<Procedures> proceduresArrayList = proceduresService.getAllProcedures();
        return new ResponseEntity<>(proceduresArrayList, (!proceduresArrayList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}

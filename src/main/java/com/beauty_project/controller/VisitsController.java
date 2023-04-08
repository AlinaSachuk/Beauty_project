package com.beauty_project.controller;

import com.beauty_project.domain.Visits;
import com.beauty_project.service.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/visit")
public class VisitsController {
    VisitsService visitsService;

    @Autowired
    public VisitsController(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Visits>> getVisitById(@PathVariable int id) {
        Optional<Visits> visit = visitsService.getVisitById(id);
        if (visit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Visits>> getAllVisits() {
        ArrayList<Visits> list = visitsService.getAllVisits();
        return new ResponseEntity<>(list, !list.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createVisit(@RequestBody Visits visit) {
        visitsService.createVisit(visit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateVisit(@RequestBody Visits visit) {
        visitsService.updateVisit(visit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Visits> deleteVisit(@PathVariable int id) {
        visitsService.deleteVisit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

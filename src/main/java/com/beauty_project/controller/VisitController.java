package com.beauty_project.controller;

import com.beauty_project.domain.Visit;
import com.beauty_project.service.VisitService;
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
@RequestMapping("/visit")
public class VisitController {
    VisitService visitService;

    private static final Logger log = LoggerFactory.getLogger(VisitController.class);

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Visit>> getVisitById(@PathVariable int id) {
        Optional<Visit> visit = visitService.getVisitById(id);
        if (visit.isEmpty()) {
            log.warn("Visit with id=" + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Visit>> getAllVisits() {
        ArrayList<Visit> list = visitService.getAllVisits();
        return new ResponseEntity<>(list, !list.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> createVisit(@RequestBody Visit visit, @PathVariable int id) {
        visitService.createVisit(visit, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateVisit(@RequestBody Visit visit) {
        visitService.updateVisit(visit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteVisit(@PathVariable int id) {
        visitService.deleteVisit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
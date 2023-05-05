package com.beauty_project.controller;

import com.beauty_project.domain.Visit;
import com.beauty_project.domain.dto.CreateVisitDto;
import com.beauty_project.domain.dto.UpdateVisitDto;
import com.beauty_project.service.VisitService;
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
@RequestMapping("/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable int id) {
        Visit visit = visitService.getVisitById(id);
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        List<Visit> list = visitService.getAllVisits();
        return new ResponseEntity<>(list, !list.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> createVisit(@RequestBody CreateVisitDto visitDto, @PathVariable int id) {
        visitService.createVisit(visitDto, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateVisit(@RequestBody UpdateVisitDto visitDto) {
        visitService.updateVisit(visitDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteVisit(@PathVariable int id) {
        visitService.deleteVisit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
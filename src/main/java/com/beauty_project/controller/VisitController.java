package com.beauty_project.controller;

import com.beauty_project.domain.request.VisitRequestDto;
import com.beauty_project.domain.response.VisitResponseDto;
import com.beauty_project.service.VisitService;
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
@RequestMapping("/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VisitResponseDto getVisitById(@PathVariable int id) {
        return visitService.getVisitById(id);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<VisitResponseDto> getAllVisits() {
        return visitService.getAllVisits();
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VisitResponseDto createVisit(@RequestBody VisitRequestDto visitDto, @PathVariable int id) {
        return visitService.createVisit(visitDto, id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VisitResponseDto updateVisit(@RequestBody VisitRequestDto visitDto) {
        return visitService.updateVisit(visitDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVisit(@PathVariable int id) {
        visitService.deleteVisit(id);
    }
}
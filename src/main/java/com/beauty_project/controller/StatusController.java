package com.beauty_project.controller;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.request.CreateUpdateStatusDto;
import com.beauty_project.service.StatusService;
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
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable int id) {
        Status status = statusService.getStatusById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> list = statusService.getAllStatus();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createStatus(@RequestBody CreateUpdateStatusDto statusDto) {
        statusService.createStatus(statusDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateStatus(@RequestBody CreateUpdateStatusDto statusDto) {
        statusService.updateStatus(statusDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStatus(@PathVariable int id) {
        statusService.deleteStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
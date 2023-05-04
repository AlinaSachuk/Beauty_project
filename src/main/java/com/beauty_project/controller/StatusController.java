package com.beauty_project.controller;

import com.beauty_project.domain.Status;
import com.beauty_project.service.StatusService;
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
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;
    private static final Logger log = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Status>> getStatusById(@PathVariable int id) {
        Optional<Status> status = statusService.getStatusById(id);
        if (status.isEmpty()) {
            log.warn("Status with id=" + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Status>> getAllStatus() {
        ArrayList<Status> list = statusService.getAllStatus();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createStatus(@RequestBody Status status) {
        statusService.createStatus(status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateStatus(@RequestBody Status status) {
        statusService.updateStatus(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStatus(@PathVariable int id) {
        statusService.deleteStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
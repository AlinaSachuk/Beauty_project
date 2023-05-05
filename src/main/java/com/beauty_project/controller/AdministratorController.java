package com.beauty_project.controller;

import com.beauty_project.domain.Administrator;
import com.beauty_project.domain.dto.RegistrationAdminDto;
import com.beauty_project.service.AdministratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    private final AdministratorService administratorService;
    private static final Logger log = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<Optional<Administrator>> getAdminByLogin(@PathVariable String login) {
        Optional<Administrator> admin = administratorService.getAdminByLogin(login);
        if (admin.isEmpty()) {
            log.warn("Admin with login=" + login + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> adminRegistration(@RequestBody RegistrationAdminDto registrationAdminDto) {
        administratorService.adminRegistration(registrationAdminDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable int id) {
        administratorService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
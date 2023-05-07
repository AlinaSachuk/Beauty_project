package com.beauty_project.controller;

import com.beauty_project.domain.request.RegistrationAdminDto;
import com.beauty_project.domain.response.AdministratorResponseDto;
import com.beauty_project.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/{login}")
    @ResponseStatus(HttpStatus.OK)
    public AdministratorResponseDto getAdminByLogin(@PathVariable String login) {
        return administratorService.getAdminByLogin(login);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdministratorResponseDto adminRegistration(@RequestBody RegistrationAdminDto registrationAdminDto) {
        return administratorService.adminRegistration(registrationAdminDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable int id) {
        administratorService.deleteAdmin(id);
    }
}
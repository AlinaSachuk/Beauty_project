package com.beauty_project.controller;

import com.beauty_project.domain.request.RegistrationAdminDto;
import com.beauty_project.domain.response.AdministratorResponseDto;
import com.beauty_project.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration/admin")
public class AdministratorController {
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdministratorResponseDto createAdmin(@RequestBody RegistrationAdminDto adminDto) {
        return administratorService.createAdmin(adminDto);
    }
}

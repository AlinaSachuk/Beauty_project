package com.beauty_project.controller;

import com.beauty_project.domain.request.StatusRequestDto;
import com.beauty_project.domain.response.StatusResponseDto;
import com.beauty_project.service.StatusService;
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
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto getStatusById(@PathVariable int id) {
        return statusService.getStatusById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatusResponseDto> getAllStatus() {
        return statusService.getAllStatus();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createStatus(@RequestBody StatusRequestDto statusDto) {
        return statusService.createStatus(statusDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateStatus(@RequestBody StatusRequestDto statusDto) {
        return statusService.updateStatus(statusDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatus(@PathVariable int id) {
        statusService.deleteStatus(id);
    }
}
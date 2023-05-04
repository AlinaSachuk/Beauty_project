package com.beauty_project.service.impl;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.dto.CreateUpdateStatusDto;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }

    public ArrayList<Status> getAllStatus() {
        return (ArrayList<Status>) statusRepository.findAll();
    }

    public Status createStatus(CreateUpdateStatusDto statusDto) {
        Status status = new Status();
        status.setStatus(statusDto.getStatus());
        status.setPercent(statusDto.getPercent());
        return statusRepository.save(status);
    }

    public Status updateStatus(CreateUpdateStatusDto statusDto) {
        Status status = statusRepository.findById(statusDto.getId())
                .orElseThrow(()-> new NotFoundException("Status by id=" + statusDto.getId() + " not found"));
        status.setStatus(statusDto.getStatus());
        status.setPercent(statusDto.getPercent());
        return statusRepository.saveAndFlush(status);
    }

    public void deleteStatus(int id) {
        statusRepository.deleteById(id);
    }
}
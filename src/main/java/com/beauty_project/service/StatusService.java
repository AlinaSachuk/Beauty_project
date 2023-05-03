package com.beauty_project.service;

import com.beauty_project.domain.Status;
import com.beauty_project.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StatusService {
    StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }

    public ArrayList<Status> getAllStatus() {
        return (ArrayList<Status>) statusRepository.findAll();
    }

    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    public Status updateStatus(Status status) {
        statusRepository.findById(status.getId()).orElseThrow(() -> new NotFoundException("Status with id=" + status.getId() + " not found: updateStatus method."));
        return statusRepository.saveAndFlush(status);
    }

    public void deleteStatus(int id) {
        statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status with id=" + id + " not found:  deleteStatus method."));
        statusRepository.deleteById(id);
    }
}
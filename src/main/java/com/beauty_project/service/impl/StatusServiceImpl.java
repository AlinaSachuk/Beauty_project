package com.beauty_project.service.impl;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.request.StatusRequestDto;
import com.beauty_project.domain.response.StatusResponseDto;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public StatusResponseDto getStatusById(int id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Status by id=" + id + " not found"));
        return new StatusResponseDto(
                status.getId(),
                status.getStatus(),
                status.getPercent()
        );
    }

    public List<StatusResponseDto> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        return statusList.stream().map(status -> new StatusResponseDto(
                status.getId(),
                status.getStatus(),
                status.getPercent()
        )).collect(Collectors.toList());
    }

    public StatusResponseDto createStatus(StatusRequestDto statusDto) {
        Status status = new Status();
        status.setStatus(statusDto.getStatus());
        status.setPercent(statusDto.getPercent());
        Status savedStatus = statusRepository.save(status);
        return new StatusResponseDto(
                savedStatus.getId(),
                savedStatus.getStatus(),
                savedStatus.getPercent()
        );
    }

    public StatusResponseDto updateStatus(StatusRequestDto statusDto) {
        Status status = statusRepository.findById(statusDto.getId())
                .orElseThrow(() -> new NotFoundException("Status by id=" + statusDto.getId() + " not found"));
        status.setStatus(statusDto.getStatus());
        status.setPercent(statusDto.getPercent());
        Status savedStatus = statusRepository.save(status);
        return new StatusResponseDto(
                savedStatus.getId(),
                savedStatus.getStatus(),
                savedStatus.getPercent()
        );
    }

    public void deleteStatus(int id) {
        statusRepository.deleteById(id);
    }
}
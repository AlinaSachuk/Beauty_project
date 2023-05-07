package com.beauty_project.service;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.request.StatusRequestDto;

import java.util.List;

public interface StatusService {
    Status getStatusById(int id);

    List<Status> getAllStatus();

    Status createStatus(StatusRequestDto statusDto);

    Status updateStatus(StatusRequestDto statusDto);

    void deleteStatus(int id);
}
package com.beauty_project.service;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.request.CreateUpdateStatusDto;

import java.util.List;

public interface StatusService {
    Status getStatusById(int id);

    List<Status> getAllStatus();

    Status createStatus(CreateUpdateStatusDto statusDto);

    Status updateStatus(CreateUpdateStatusDto statusDto);

    void deleteStatus(int id);
}
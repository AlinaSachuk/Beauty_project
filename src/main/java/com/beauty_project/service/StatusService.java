package com.beauty_project.service;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.dto.CreateUpdateStatusDto;

import java.util.ArrayList;
import java.util.Optional;

public interface StatusService {
    Optional<Status> getStatusById(int id);

    ArrayList<Status> getAllStatus();

    Status createStatus(CreateUpdateStatusDto statusDto);

    Status updateStatus(CreateUpdateStatusDto statusDto);

    void deleteStatus(int id);
}
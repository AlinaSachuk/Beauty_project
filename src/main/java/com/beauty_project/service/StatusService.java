package com.beauty_project.service;

import com.beauty_project.domain.Status;

import java.util.ArrayList;
import java.util.Optional;

public interface StatusService {
    Optional<Status> getStatusById(int id);

    ArrayList<Status> getAllStatus();

    Status createStatus(Status status);

    Status updateStatus(Status status);

    void deleteStatus(int id);
}
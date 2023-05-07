package com.beauty_project.service;

import com.beauty_project.domain.request.StatusRequestDto;
import com.beauty_project.domain.response.StatusResponseDto;

import java.util.List;

public interface StatusService {
    StatusResponseDto getStatusById(int id);

    List<StatusResponseDto> getAllStatus();

    StatusResponseDto createStatus(StatusRequestDto statusDto);

    StatusResponseDto updateStatus(StatusRequestDto statusDto);

    void deleteStatus(int id);
}
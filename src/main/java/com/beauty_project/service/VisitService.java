package com.beauty_project.service;

import com.beauty_project.domain.request.VisitRequestDto;
import com.beauty_project.domain.response.VisitResponseDto;

import java.util.List;

public interface VisitService {

    VisitResponseDto getVisitById(int id);

    List<VisitResponseDto> getAllVisits();

    List<VisitResponseDto> getAllVisitsForSingleCustomer(int id);

    VisitResponseDto createVisit(VisitRequestDto visitDto, int id);

    VisitResponseDto updateVisit(VisitRequestDto visitDto);

    void deleteVisit(int id);
}
package com.beauty_project.service;

import com.beauty_project.domain.Visit;
import com.beauty_project.domain.request.VisitRequestDto;

import java.util.List;

public interface VisitService {
    Visit getVisitById(int id);

    List<Visit> getAllVisits();

    List<Visit> getAllVisitsForSingleCustomer(int id);

    Visit createVisit(VisitRequestDto visitDto, int id);

    Visit updateVisit(VisitUpdateRequestDto visitDto);

    void deleteVisit(int id);
}
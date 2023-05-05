package com.beauty_project.service;

import com.beauty_project.domain.Visit;
import com.beauty_project.domain.dto.CreateVisitDto;
import com.beauty_project.domain.dto.UpdateVisitDto;

import java.util.List;

public interface VisitService {
    Visit getVisitById(int id);

    List<Visit> getAllVisits();

    List<Visit> getAllVisitsForSingleCustomer(int id);

    Visit createVisit(CreateVisitDto visitDto, int id);

    Visit updateVisit(UpdateVisitDto visitDto);

    void deleteVisit(int id);
}
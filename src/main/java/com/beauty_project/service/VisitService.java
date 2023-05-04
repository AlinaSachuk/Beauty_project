package com.beauty_project.service;

import com.beauty_project.domain.Visit;
import com.beauty_project.domain.dto.CreateVisitDto;
import com.beauty_project.domain.dto.UpdateVisitDto;

import java.util.ArrayList;
import java.util.Optional;

public interface VisitService {
    Optional<Visit> getVisitById(int id);

    ArrayList<Visit> getAllVisits();

    ArrayList<Visit> getAllVisitsForSingleCustomer(int id);

    Visit createVisit(CreateVisitDto visitDto, int id);

    Visit updateVisit(UpdateVisitDto visitDto);

    void deleteVisit(int id);
}
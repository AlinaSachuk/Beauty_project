package com.beauty_project.service;

import com.beauty_project.domain.Visit;

import java.util.ArrayList;
import java.util.Optional;

public interface VisitService {
    Optional<Visit> getVisitById(int id);

    ArrayList<Visit> getAllVisits();

    ArrayList<Visit> getAllVisitsForSingleCustomer(int id);

    Visit createVisit(Visit visit, int id);

    Visit updateVisit(Visit visit);

    void deleteVisit(int id);
}
package com.beauty_project.service;

import com.beauty_project.domain.Visits;
import com.beauty_project.repository.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitsService {
    VisitsRepository visitsRepository;

    @Autowired
    public VisitsService(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    public Optional<Visits> getVisitById(int id) {
        return visitsRepository.findById(id);
    }

    public ArrayList<Visits> getAllVisits() {
        return (ArrayList<Visits>) visitsRepository.findAll();
    }

    public Visits createVisit (Visits visit) {
        return visitsRepository.save(visit);
    }

    public Visits updateVisit (Visits visit) {
        return visitsRepository.saveAndFlush(visit);
    }

    public void deleteVisit (int id) {
        visitsRepository.deleteById(id);
    }
}

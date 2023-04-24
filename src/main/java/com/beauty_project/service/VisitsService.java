package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitsService {
    VisitsRepository visitsRepository;
    CustomerRepository customerRepository;

    @Autowired
    public VisitsService(VisitsRepository visitsRepository, CustomerRepository customerRepository) {
        this.visitsRepository = visitsRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Visit> getVisitById(int id) {
        return visitsRepository.findById(id);
    }

    public ArrayList<Visit> getAllVisits() {
        return (ArrayList<Visit>) visitsRepository.findAll();
    }

    public Visit createVisit(Visit visit, int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer by id not found: " + id));
        visit.setCustomerId(customer.getId());
//        customer.getStatus().getPercent()
        return visitsRepository.save(visit);
    }

    public Visit updateVisit(Visit visit) {
        return visitsRepository.saveAndFlush(visit);
    }

    public void deleteVisit(int id) {
        visitsRepository.deleteById(id);
    }
}

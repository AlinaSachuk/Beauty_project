package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitService {
    VisitRepository visitRepository;
    CustomerRepository customerRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository, CustomerRepository customerRepository) {
        this.visitRepository = visitRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Visit> getVisitById(int id) {
        return visitRepository.findById(id);
    }

    public ArrayList<Visit> getAllVisits() {
        return (ArrayList<Visit>) visitRepository.findAll();
    }

    public Visit createVisit(Visit visit, int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer by id not found: " + id));
        visit.setCustomerId(customer.getId());
//        customer.getStatus().getPercent()
//        setFinalPrice(.getFinalPrice() *(100-status.percent)/100)
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Visit visit) {
        return visitRepository.saveAndFlush(visit);
    }

    public void deleteVisit(int id) {
        visitRepository.deleteById(id);
    }
}

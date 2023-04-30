package com.beauty_project.service;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitService {
    VisitRepository visitRepository;

    CustomerRepository customerRepository;

    StatusRepository statusRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository, CustomerRepository customerRepository, StatusRepository statusRepository) {
        this.visitRepository = visitRepository;
        this.customerRepository = customerRepository;
        this.statusRepository = statusRepository;
    }

    public Optional<Visit> getVisitById(int id) {
        return visitRepository.findById(id);
    }

    public ArrayList<Visit> getAllVisits() {
        return (ArrayList<Visit>) visitRepository.findAll();
    }

    public ArrayList<Visit> getAllVisitsForSingleCustomer(int id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer by id not found: " + id));
        return (ArrayList<Visit>) visitRepository.findVisitsByCustomerId(id);
    }

    public Visit createVisit(Visit visit, int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer by id not found: " + id));
        visit.setCustomerId(customer.getId());
        if (customer.getStatus() == null) {
            visit.setFinalPrice(visit.getFinalPrice());
        }
        int discountPercent = statusRepository.findPercentByStatus(customer.getStatus());
        visit.setFinalPrice(visit.getFinalPrice() * (100 - discountPercent) / 100);
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Visit visit) {
        return visitRepository.saveAndFlush(visit);
    }

    public void deleteVisit(int id) {
        visitRepository.deleteById(id);
    }
}
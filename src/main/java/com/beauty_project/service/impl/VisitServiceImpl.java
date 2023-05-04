package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.repository.VisitRepository;
import com.beauty_project.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final CustomerRepository customerRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository, CustomerRepository customerRepository, StatusRepository statusRepository) {
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
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found."));
        return (ArrayList<Visit>) visitRepository.findVisitsByCustomerId(id);
    }

    public Visit createVisit(Visit visit, int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found."));
        visit.setCustomerId(customer.getId());
        if (customer.getStatus() == null) {
            visit.setFinalPrice(visit.getFinalPrice());
        }
        int discountPercent = statusRepository.findPercentByStatus(customer.getStatus());
        visit.setFinalPrice(visit.getFinalPrice() * (100 - discountPercent) / 100);
        if (visit.getFinalPrice() == 0 | visit.getFinalPrice() < 0) {
            throw new ArithmeticException("Incorrect price: " + visit.getFinalPrice());
        }
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Visit visit) {
        return visitRepository.saveAndFlush(visit);
    }

    public void deleteVisit(int id) {
        visitRepository.deleteById(id);
    }
}
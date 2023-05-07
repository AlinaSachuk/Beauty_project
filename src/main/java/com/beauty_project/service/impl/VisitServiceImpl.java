package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.domain.request.VisitRequestDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.repository.VisitRepository;
import com.beauty_project.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

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

    public Visit getVisitById(int id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit by id=" + id + " not found."));
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public List<Visit> getAllVisitsForSingleCustomer(int id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found."));
        return visitRepository.findVisitsByCustomerId(id);
    }

    public Visit createVisit(VisitRequestDto visitDto, int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found."));
        Visit visit = new Visit();
        visit.setDateOfVisit(visitDto.getDateOfVisit());
        visit.setCustomerId(customer.getId());
        if (customer.getStatus() == null) {
            visit.setFinalPrice(visitDto.getFinalPrice());
        }
        int discountPercent = statusRepository.findPercentByStatus(customer.getStatus());
        visit.setFinalPrice(visitDto.getFinalPrice() * (100 - discountPercent) / 100);
        if (visit.getFinalPrice() == 0 | visit.getFinalPrice() < 0) {
            throw new ArithmeticException("Incorrect price: " + visit.getFinalPrice());
        }
        return visitRepository.save(visit);
    }

    public Visit updateVisit(VisitUpdateRequestDto visitDto) {
        Visit visit = visitRepository.findById(visitDto.getId())
                .orElseThrow(()-> new NotFoundException("Visit by id=" + visitDto.getId() + " not found"));
        visit.setDateOfVisit(visitDto.getDateOfVisit());
        visit.setFinalPrice(visitDto.getFinalPrice());
        if (visit.getFinalPrice() == 0 | visit.getFinalPrice() < 0) {
            throw new ArithmeticException("Incorrect price: " + visit.getFinalPrice());
        }
        return visitRepository.saveAndFlush(visit);
    }

    public void deleteVisit(int id) {
        visitRepository.deleteById(id);
    }
}
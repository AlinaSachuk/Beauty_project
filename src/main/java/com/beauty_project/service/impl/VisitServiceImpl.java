package com.beauty_project.service.impl;

import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Visit;
import com.beauty_project.domain.request.VisitRequestDto;
import com.beauty_project.domain.response.VisitResponseDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.repository.VisitRepository;
import com.beauty_project.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public VisitResponseDto getVisitById(int id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit by id=" + id + " not found."));
        return new VisitResponseDto(
                visit.getId(),
                visit.getDateOfVisit(),
                visit.getFinalPrice(),
                visit.getCustomerId()
        );
    }

    @Override
    public List<VisitResponseDto> getAllVisits() {
        List<Visit> visits = visitRepository.findAll();
        return visits.stream().map(visit -> new VisitResponseDto(
                visit.getId(),
                visit.getDateOfVisit(),
                visit.getFinalPrice(),
                visit.getCustomerId()
        )).collect(Collectors.toList());
    }

    @Override
    public List<VisitResponseDto> getAllVisitsForSingleCustomer(int id) {
        String authenticationLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=" + id + " not found."));
        if (authenticationLogin.equals(customer.getTelephoneNumber())) {
            List<Visit> visits = visitRepository.findVisitsByCustomerId(id);
            return visits.stream().map(visit -> new VisitResponseDto(
                    visit.getId(),
                    visit.getDateOfVisit(),
                    visit.getFinalPrice(),
                    visit.getCustomerId()
            )).collect(Collectors.toList());
        } else {
            throw new AuthorizationServiceException("Customer with login=" + authenticationLogin
                    + " trying to get forbidden information about visits.");
        }
    }

    @Override
    public VisitResponseDto createVisit(VisitRequestDto visitDto, int id) {
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
        Visit savedVisit = visitRepository.save(visit);
        return new VisitResponseDto(
                savedVisit.getId(),
                savedVisit.getDateOfVisit(),
                savedVisit.getFinalPrice(),
                savedVisit.getCustomerId()
        );
    }

    @Override
    public VisitResponseDto updateVisit(VisitRequestDto visitDto) {
        Visit visit = visitRepository.findById(visitDto.getId())
                .orElseThrow(() -> new NotFoundException("Visit by id=" + visitDto.getId() + " not found"));
        visit.setDateOfVisit(visitDto.getDateOfVisit());
        visit.setFinalPrice(visitDto.getFinalPrice());
        if (visit.getFinalPrice() == 0 | visit.getFinalPrice() < 0) {
            throw new ArithmeticException("Incorrect price: " + visit.getFinalPrice());
        }
        Visit savedVisit = visitRepository.save(visit);
        return new VisitResponseDto(
                savedVisit.getId(),
                savedVisit.getDateOfVisit(),
                savedVisit.getFinalPrice(),
                savedVisit.getCustomerId()
        );
    }

    public void deleteVisit(int id) {
        visitRepository.deleteById(id);
    }
}
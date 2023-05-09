package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.Visit;
import com.beauty_project.domain.request.VisitRequestDto;
import com.beauty_project.domain.response.VisitResponseDto;
import com.beauty_project.repository.CustomerRepository;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.repository.VisitRepository;
import com.beauty_project.service.impl.VisitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitServiceImplTest {
    @Mock
    private VisitRepository visitRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    StatusRepository statusRepository;
    @InjectMocks
    private VisitServiceImpl visitService;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(visitRepository).findById(1);
        assertThrows(NotFoundException.class, () -> visitService.getVisitById(1));
    }

    @Test
    public void testGetById() {
        var visit = Utils.createVisit(1, Date.valueOf("2023-05-07"), 100.0, 1);
        when(visitRepository.findById(1)).thenReturn(Optional.of(visit));
        VisitResponseDto actual = visitService.getVisitById(1);
        assertNotNull(actual);
        assertEquals(new VisitResponseDto(1, Date.valueOf("2023-05-07"), 100.0, 1), actual);
    }

    @Test
    public void testGetAllVisits() {
        Visit visit1 = Utils.createVisit(1, Date.valueOf("2023-05-07"), 100.0, 1);
        Visit visit2 = Utils.createVisit(2, Date.valueOf("2023-05-07"), 100.0, 1);
        List<Visit> allVisits = new ArrayList<>();
        allVisits.add(visit1);
        allVisits.add(visit2);
        when(visitRepository.findAll()).thenReturn(allVisits);
        List<Visit> actual = visitRepository.findAll();
        verify(visitRepository, times(1)).findAll();
        assertEquals(2, actual.size());
    }

    @Test
    public void testSave() {
        var visit = Utils.createVisit(1, Date.valueOf("2023-05-07"), 100.0, 1);
        var customer = Utils.createCustomer(1, "Bob", Date.valueOf("2000-05-09")
                , "80291235689", "bob2000@gmail.com", "bob2000"
                , "SILVER", "bob2000");
        var status = Utils.createStatus("SILVER", 5, 1);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(statusRepository.findPercentByStatus("SILVER")).thenReturn(5);
        when(visitRepository.save(any())).thenReturn(visit);
        VisitResponseDto actual = visitService.createVisit(new VisitRequestDto(1, Date.valueOf("2023-05-07")
                , 100.0, 1), 1);
        assertNotNull(actual);
        assertEquals(new VisitResponseDto(1, Date.valueOf("2023-05-07"), 100.0, 1), actual);
    }

    @Test
    public void testUpdate() {
        var savedVisit = Utils.createVisit(1, Date.valueOf("2023-05-07"), 100.0, 1);
        var updatedVisit = Utils.createVisit(1, Date.valueOf("2023-05-09"), 110.0, 1);
        when(visitRepository.findById(1)).thenReturn(Optional.of(savedVisit));
        when(visitRepository.save(any())).thenReturn(updatedVisit);
        VisitResponseDto actual = visitService.updateVisit(new VisitRequestDto(1, Date.valueOf("2023-05-07")
                , 100.0, 1));
        assertNotNull(actual);
        assertEquals(new VisitResponseDto(1, Date.valueOf("2023-05-09"), 110.0, 1), actual);
    }

    @Test
    public void testDeleteVisitById() {
        visitService.deleteVisit(1);
        verify(visitRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteVisitByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(visitRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> visitService.deleteVisit(1));
    }
}
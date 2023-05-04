package com.beauty_project.service;

import com.beauty_project.domain.Visit;
import com.beauty_project.repository.VisitRepository;
import com.beauty_project.service.impl.VisitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitServiceImplTest {
    @Mock
    private VisitRepository visitRepository;
    @InjectMocks
    private VisitServiceImpl visitService;
    private int id;
    private Visit visit;
    private ArrayList<Visit> allVisits;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(visitRepository).findById(id);
        assertThrows(NotFoundException.class, () -> visitService.getVisitById(id));
    }

    @Test
    public void testGetAllVisits() {
        when(visitRepository.findAll()).thenReturn(allVisits);
        ArrayList<Visit> returnedVisits = visitService.getAllVisits();
        verify(visitRepository).findAll();
        assertEquals(allVisits, returnedVisits);
    }
    @Test
    public void testDeleteVisitByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(visitRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> visitService.deleteVisit(id));
    }
}
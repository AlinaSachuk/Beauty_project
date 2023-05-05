package com.beauty_project.service;

import com.beauty_project.domain.Procedure;
import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.impl.ProcedureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcedureServiceImplTest {
    @Mock
    private ProcedureRepository procedureRepository;
    @InjectMocks
    private ProcedureServiceImpl procedureService;
    private int id;
    private Procedure procedure;
    private List<Procedure> allProcedures;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(procedureRepository).findById(id);
        assertThrows(NotFoundException.class, () -> procedureService.getProcedureById(id));
    }

    @Test
    public void testGetAllProcedures() {
        when(procedureRepository.findAll()).thenReturn(allProcedures);
        List<Procedure> returnedProcedures = procedureService.getAllProcedures();
        verify(procedureRepository).findAll();
        assertEquals(allProcedures, returnedProcedures);
    }
    @Test
    public void testDeleteProcedureByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(procedureRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> procedureService.deleteProcedure(id));
    }
}
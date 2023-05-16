package com.beauty_project.service;

import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.impl.ProcedureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProcedureServiceImplTest {
    @Mock
    private ProcedureRepository procedureRepository;
    @InjectMocks
    private ProcedureServiceImpl procedureService;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(procedureRepository).findById(1);
        assertThrows(NotFoundException.class, () -> procedureService.getProcedureById(1));
    }

    @Test
    public void testDeleteProcedureById() {
        procedureService.deleteProcedure(1);
        verify(procedureRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteProcedureByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(procedureRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> procedureService.deleteProcedure(1));
    }
}
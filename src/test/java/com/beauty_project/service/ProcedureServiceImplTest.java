package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.request.ProcedureRequestDto;
import com.beauty_project.domain.response.ProcedureResponseDto;
import com.beauty_project.repository.ProcedureRepository;
import com.beauty_project.service.impl.ProcedureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void testGetById() {
        var procedure = Utils.createProcedure(1, "Massage"
                , 60, 25, "relax facial massage");
        when(procedureRepository.findById(1)).thenReturn(Optional.of(procedure));
        ProcedureResponseDto actual = procedureService.getProcedureById(1);
        assertNotNull(actual);
        assertEquals(new ProcedureResponseDto(1, "Massage"
                , 60, 25, "relax facial massage"), actual);
    }

    @Test
    public void testGetAllProcedures() {
        Procedure procedure1 = Utils.createProcedure(1, "Massage"
                , 60, 25, "relax facial massage");
        Procedure procedure2 = Utils.createProcedure(2, "Massage"
                , 60, 25, "relax facial massage");
        List<Procedure> allProcedures = new ArrayList<>();
        allProcedures.add(procedure1);
        allProcedures.add(procedure2);
        when(procedureRepository.findAll()).thenReturn(allProcedures);
        List<Procedure> actual = procedureRepository.findAll();
        verify(procedureRepository, times(1)).findAll();
        assertEquals(2, actual.size());
    }

    @Test
    public void testSave() {
        var procedure = Utils.createProcedure(1, "Massage"
                , 60, 25, "relax facial massage");
        when(procedureRepository.save(any())).thenReturn(procedure);
        ProcedureResponseDto actual = procedureService.createProcedure(new ProcedureRequestDto(
                1, "Massage", 60, 25, "relax facial massage"));
        assertNotNull(actual);
        assertEquals(new ProcedureResponseDto(1, "Massage"
                , 60, 25, "relax facial massage"), actual);
    }

    @Test
    public void testUpdate() {
        var savedProcedure = Utils.createProcedure(1, "Massage"
                , 60, 25, "relax facial massage");
        var updatedProcedure = Utils.createProcedure(1, "Massage"
                , 55, 30, "relax facial massage");
        when(procedureRepository.findById(1)).thenReturn(Optional.of(savedProcedure));
        when(procedureRepository.save(any())).thenReturn(updatedProcedure);
        ProcedureResponseDto actual = procedureService.updateProcedure(new ProcedureRequestDto(
                1, "Massage", 60, 25, "relax facial massage"));
        assertNotNull(actual);
        assertEquals(new ProcedureResponseDto(1, "Massage"
                , 55, 30, "relax facial massage"), actual);
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
package com.beauty_project.service;

import com.beauty_project.domain.Status;
import com.beauty_project.domain.dto.CreateUpdateStatusDto;
import com.beauty_project.repository.StatusRepository;
import com.beauty_project.service.impl.StatusServiceImpl;
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
public class StatusServiceImplTest {
    @Mock
    private StatusRepository statusRepository;
    @InjectMocks
    private StatusServiceImpl statusService;
    private int id;
    private Status status;
    private CreateUpdateStatusDto statusDto;
    private ArrayList<Status> allStatus;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(statusRepository).findById(id);
        assertThrows(NotFoundException.class, () -> statusService.getStatusById(id));
    }

    @Test
    public void testGetAllStatus() {
        when(statusRepository.findAll()).thenReturn(allStatus);
        ArrayList<Status> returnedStatus = statusService.getAllStatus();
        verify(statusRepository).findAll();
        assertEquals(allStatus, returnedStatus);
    }
    @Test
    public void testDeleteStatusByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(statusRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> statusService.deleteStatus(id));
    }
}
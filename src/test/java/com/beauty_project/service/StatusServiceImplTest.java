package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.Status;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatusServiceImplTest {
    @Mock
    private StatusRepository statusRepository;
    @InjectMocks
    private StatusServiceImpl statusService;

    @Test
    public void testGetByIdWithNotFoundException() {
        doThrow(NotFoundException.class).when(statusRepository).findById(1);
        assertThrows(NotFoundException.class, () -> statusService.getStatusById(1));
    }

    @Test
    public void testGetAllStatus() {
        Status status1 = Utils.createStatus("SILVER", 5, 1);
        Status status2 = Utils.createStatus("GOLD", 10, 2);
        List<Status> allStatus = new ArrayList<>();
        allStatus.add(status1);
        allStatus.add(status2);
        when(statusRepository.findAll()).thenReturn(allStatus);
        List<Status> actual = statusRepository.findAll();
        verify(statusRepository, times(1)).findAll();
        assertEquals(2, actual.size());
    }

    @Test
    public void testDeleteStatusById() {
        statusService.deleteStatus(1);
        verify(statusRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteStatusByIdWithEmptyResultDataAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(statusRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> statusService.deleteStatus(1));
    }
}
package com.beauty_project.service;

import com.beauty_project.Utils;
import com.beauty_project.domain.response.AdministratorResponseDto;
import com.beauty_project.repository.AdministratorRepository;
import com.beauty_project.service.impl.AdministratorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdministratorServiceImplTest {
    @Mock
    private AdministratorRepository administratorRepository;

    @InjectMocks
    private AdministratorServiceImpl administratorService;

    @Test
    public void testGetByLoginWithNotFoundException() {
        doThrow(NotFoundException.class).when(administratorRepository).findAdministratorByLogin("Admin");
        assertThrows(NotFoundException.class, () -> administratorService.getAdminByLogin("Admin"));
    }

    @Test
    public void testGetByLogin() {
        var admin = Utils.createAdministrator(1, "Admin", "admin", "ADMIN");
        when(administratorRepository.findAdministratorByLogin("Admin")).thenReturn(Optional.of(admin));
        AdministratorResponseDto actual = administratorService.getAdminByLogin("Admin");
        assertNotNull(actual);
        assertEquals(new AdministratorResponseDto(1, "Admin"), actual);
    }

    @Test
    public void testDeleteById() {
        administratorService.deleteAdmin(1);
        verify(administratorRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdEmptyAccessException() {
        doThrow(EmptyResultDataAccessException.class).when(administratorRepository).deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> administratorService.deleteAdmin(1));
    }
}
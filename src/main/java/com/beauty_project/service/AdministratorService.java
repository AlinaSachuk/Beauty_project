package com.beauty_project.service;

import com.beauty_project.domain.request.RegistrationAdminDto;
import com.beauty_project.domain.response.AdministratorResponseDto;

public interface AdministratorService {
    AdministratorResponseDto createAdmin(RegistrationAdminDto adminDto);
}

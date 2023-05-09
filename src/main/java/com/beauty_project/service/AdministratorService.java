package com.beauty_project.service;

import com.beauty_project.domain.request.RegistrationAdminDto;
import com.beauty_project.domain.response.AdministratorResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface AdministratorService {

    AdministratorResponseDto getAdminByLogin(@PathVariable String login);

    AdministratorResponseDto adminRegistration(RegistrationAdminDto registrationAdminDto);

    void deleteAdmin(@PathVariable int id);
}
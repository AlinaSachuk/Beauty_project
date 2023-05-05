package com.beauty_project.service;

import com.beauty_project.domain.Administrator;
import com.beauty_project.domain.dto.RegistrationAdminDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface AdministratorService {
    Optional<Administrator> getAdminByLogin(@PathVariable String login);

    Administrator adminRegistration(RegistrationAdminDto registrationAdminDto);

    void deleteAdmin(@PathVariable int id);
}

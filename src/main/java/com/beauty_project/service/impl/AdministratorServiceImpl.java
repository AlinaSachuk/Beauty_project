package com.beauty_project.service.impl;

import com.beauty_project.domain.Administrator;
import com.beauty_project.domain.request.RegistrationAdminDto;
import com.beauty_project.domain.response.AdministratorResponseDto;
import com.beauty_project.repository.AdministratorRepository;
import com.beauty_project.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;
    private final String ADMIN_ROLE = "ADMIN";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository, PasswordEncoder passwordEncoder) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdministratorResponseDto getAdminByLogin(String login) {
        Administrator administrator = administratorRepository.findAdministratorByLogin(login)
                .orElseThrow(() -> new NotFoundException("Administrator by login=" + login + " not found"));
        return new AdministratorResponseDto(administrator.getId(), administrator.getLogin());
    }

    @Override
    public AdministratorResponseDto adminRegistration(RegistrationAdminDto registrationAdminDto) {
        Administrator administrator = new Administrator();
        administrator.setLogin(registrationAdminDto.getLogin());
        administrator.setPassword(passwordEncoder.encode(registrationAdminDto.getPassword()));
        administrator.setRole(ADMIN_ROLE);
        Administrator savedAdministrator = administratorRepository.save(administrator);
        return new AdministratorResponseDto(savedAdministrator.getId(), savedAdministrator.getLogin());
    }

    @Override
    public void deleteAdmin(int id) {
        administratorRepository.deleteById(id);
    }
}
package com.beauty_project.service.impl;

import com.beauty_project.domain.Administrator;
import com.beauty_project.domain.dto.RegistrationAdminDto;
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
    public Administrator getAdminByLogin(String login) {
        return administratorRepository.findAdministratorByLogin(login)
                .orElseThrow(()-> new NotFoundException("Administrator by login=" + login + " not found"));
    }

    @Override
    public Administrator adminRegistration(RegistrationAdminDto registrationAdminDto) {
        Administrator administrator = new Administrator();
        administrator.setLogin(registrationAdminDto.getLogin());
        administrator.setPassword(passwordEncoder.encode(registrationAdminDto.getPassword()));
        administrator.setRole(ADMIN_ROLE);
        return administratorRepository.save(administrator);
    }

    @Override
    public void deleteAdmin(int id) {
        administratorRepository.deleteById(id);
    }
}
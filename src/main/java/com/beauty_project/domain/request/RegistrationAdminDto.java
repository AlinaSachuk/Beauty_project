package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrationAdminDto {
    private int id;
    private String login;
    private String password;
}
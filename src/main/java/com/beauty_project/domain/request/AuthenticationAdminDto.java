package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationAdminDto {
    private String login;
    private String password;
}
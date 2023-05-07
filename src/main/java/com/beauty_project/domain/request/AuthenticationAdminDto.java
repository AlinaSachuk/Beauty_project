package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationAdminDto {
    private String login;
    private String password;
}
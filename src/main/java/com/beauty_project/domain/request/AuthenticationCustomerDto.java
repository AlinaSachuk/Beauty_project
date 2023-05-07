package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
public class AuthenticationCustomerDto {

    @Pattern(regexp = "[0-9]{11}")
    private String telephoneNumber;

    @Size(min = 5)
    private String password;
}
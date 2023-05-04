package com.beauty_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AuthenticationCustomerDto {

    @Pattern(regexp = "[0-9]{11}")
    private String telephoneNumber;

    @Size(min = 5)
    private String password;
}
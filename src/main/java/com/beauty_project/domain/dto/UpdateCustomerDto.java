package com.beauty_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UpdateCustomerDto {
    private String customerName;

    @Pattern(regexp = "[0-9]{11}")
    private String telephoneNumber;

    @Email
    private String email;
    private String instagramAccount;

    @Size(min = 5)
    private String password;
}
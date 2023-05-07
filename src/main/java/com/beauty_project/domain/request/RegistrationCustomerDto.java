package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
public class RegistrationCustomerDto {
    private Integer id;
    private String customerName;
    private Date birthDate;

    @Pattern(regexp = "[0-9]{11}")
    private String telephoneNumber;

    @Email
    private String email;
    private String instagramAccount;

    @Size(min = 5)
    private String password;
}
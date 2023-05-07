package com.beauty_project.domain.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerResponseDto {
    private int id;
    private String customerName;
    private String telephoneNumber;
    private String email;
    private String instagramAccount;
}
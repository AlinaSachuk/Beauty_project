package com.beauty_project.domain.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private String customerName;
    private String telephoneNumber;
    private String email;
    private String instagramAccount;
}
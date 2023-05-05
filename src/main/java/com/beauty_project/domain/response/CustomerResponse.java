package com.beauty_project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private String customerName;
    private String telephoneNumber;
    private String email;
    private String instagramAccount;
}
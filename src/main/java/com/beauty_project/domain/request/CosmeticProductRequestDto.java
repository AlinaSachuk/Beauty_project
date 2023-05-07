package com.beauty_project.domain.request;

import lombok.Data;

@Data
public class CosmeticProductRequestDto {
    private int id;
    private String productName;
    private String manufacture;
    private String countryOfOrigin;
}
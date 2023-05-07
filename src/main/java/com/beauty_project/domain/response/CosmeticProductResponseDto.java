package com.beauty_project.domain.response;

import lombok.Data;

@Data
public class CosmeticProductResponseDto {
    private int id;
    private String productName;
    private String manufacture;
    private String countryOfOrigin;
}
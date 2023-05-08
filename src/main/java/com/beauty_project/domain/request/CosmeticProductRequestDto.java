package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CosmeticProductRequestDto {
    private int id;
    private String productName;
    private String manufacture;
    private String countryOfOrigin;
}
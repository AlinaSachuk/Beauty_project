package com.beauty_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUpdateProcedureDto {
    private Integer id;
    private String serviceName;
    private int duration;
    private int price;
    private String description;
}
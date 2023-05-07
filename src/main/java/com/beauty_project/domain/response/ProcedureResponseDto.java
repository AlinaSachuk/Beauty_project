package com.beauty_project.domain.response;

import lombok.Data;

@Data
public class ProcedureResponseDto {
    private Integer id;
    private String serviceName;
    private int duration;
    private int price;
    private String description;
}

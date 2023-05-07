package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProcedureRequestDto {
    private Integer id;
    private String serviceName;
    private int duration;
    private int price;
    private String description;
}
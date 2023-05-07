package com.beauty_project.domain.request;

import lombok.Data;

@Data
public class StatusRequestDto {
    private int id;
    private String status;
    private int percent;
}
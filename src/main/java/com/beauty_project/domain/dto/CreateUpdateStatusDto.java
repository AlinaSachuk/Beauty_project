package com.beauty_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUpdateStatusDto {
    private String status;
    private int percent;
}
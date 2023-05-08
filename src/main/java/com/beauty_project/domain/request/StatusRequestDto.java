package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusRequestDto {
    private int id;
    private String status;
    private int percent;
}
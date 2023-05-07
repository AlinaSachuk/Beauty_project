package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusRequestDto {
    private int id;
    private String status;
    private int percent;
}
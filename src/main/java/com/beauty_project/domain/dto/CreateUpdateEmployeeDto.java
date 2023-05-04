package com.beauty_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUpdateEmployeeDto {
    private String employeeName;
    private String position;
    private String education;
    private String workExperience;
}
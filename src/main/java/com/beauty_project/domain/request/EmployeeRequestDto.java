package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequestDto {
    private Integer id;
    private String employeeName;
    private String position;
    private String education;
    private String workExperience;
}
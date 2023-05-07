package com.beauty_project.domain.request;

import lombok.Data;

@Data
public class EmployeeRequestDto {
    private Integer id;
    private String employeeName;
    private String position;
    private String education;
    private String workExperience;
}
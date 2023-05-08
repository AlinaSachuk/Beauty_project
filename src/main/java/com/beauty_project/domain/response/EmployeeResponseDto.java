package com.beauty_project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponseDto {
    private Integer id;
    private String employeeName;
    private String position;
    private String education;
    private String workExperience;
}
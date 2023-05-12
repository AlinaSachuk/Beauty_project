package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerStatusUpdateRequestDto {
    private Integer id;
    private String customerName;
    private String status;
}
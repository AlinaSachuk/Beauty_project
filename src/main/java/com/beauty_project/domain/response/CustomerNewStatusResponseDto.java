package com.beauty_project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerNewStatusResponseDto {
    private Integer id;
    private String customerName;
    private String status;
}

package com.beauty_project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class VisitResponseDto {
    private Integer id;
    private Date dateOfVisit;
    private double finalPrice;
    private int customerId;
}
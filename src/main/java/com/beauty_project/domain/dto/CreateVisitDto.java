package com.beauty_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class CreateVisitDto {
    private Integer id;
    private Date dateOfVisit;
    private double finalPrice;
    private int customerId;
}
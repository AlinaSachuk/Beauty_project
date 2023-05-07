package com.beauty_project.domain.response;

import lombok.AllArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
public class VisitResponseDto {
    private Integer id;
    private Date dateOfVisit;
    private double finalPrice;
    private int customerId;
}
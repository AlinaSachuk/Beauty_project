package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
public class VisitRequestDto {
    private Integer id;
    private Date dateOfVisit;
    private double finalPrice;
    private int customerId;
}
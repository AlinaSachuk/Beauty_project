package com.beauty_project.domain.request;

import lombok.Data;

import java.sql.Date;

@Data
public class VisitRequestDto {
    private Integer id;
    private Date dateOfVisit;
    private double finalPrice;
    private int customerId;
}
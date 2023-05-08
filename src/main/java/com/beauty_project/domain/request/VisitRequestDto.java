package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class VisitRequestDto {
    private Integer id;
    private Date dateOfVisit;
    private double finalPrice;
    private int customerId;
}
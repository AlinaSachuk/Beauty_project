package com.beauty_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
public class ProcedureRequestDto {
    private Integer id;
    private String serviceName;
    private int duration;
    private int price;
    private String description;
    @NotEmpty
    private List<Integer> cosmeticProductIds;
}
package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cosmetic_products")
public class CosmeticProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq_gen")
    @SequenceGenerator(name = "product_id_seq_gen", sequenceName = "cosmetic_products_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;
}

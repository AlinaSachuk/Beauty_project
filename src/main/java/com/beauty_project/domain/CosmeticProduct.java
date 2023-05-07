package com.beauty_project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "cosmetic_products")
@ToString(exclude = "procedureList")
@EqualsAndHashCode(exclude = "procedureList")
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

    @JsonBackReference
    @ManyToMany(mappedBy = "productList")
    private Set<Procedure> procedureList = new HashSet<>();
}
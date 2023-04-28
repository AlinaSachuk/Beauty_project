package com.beauty_project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "procedures")
@ToString(exclude = "productList")
@EqualsAndHashCode(exclude = "productList")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq_gen")
    @SequenceGenerator(name = "service_id_seq_gen", sequenceName = "services_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "duration")
    private int duration;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "l_proced_prod",
            joinColumns = {@JoinColumn(name = "procedure_id")},
            inverseJoinColumns = {@JoinColumn(name = "prod_id")})
    private Set<CosmeticProduct> productList = new HashSet<>();
}

package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "procedures")
public class Procedures {
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
}

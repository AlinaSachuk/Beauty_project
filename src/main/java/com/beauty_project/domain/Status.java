package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_id_seq_gen")
    @SequenceGenerator(name = "status_id_seq_gen", sequenceName = "status_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "percent")
    private int percent;

}

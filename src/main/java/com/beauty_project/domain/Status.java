package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "status_table")
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
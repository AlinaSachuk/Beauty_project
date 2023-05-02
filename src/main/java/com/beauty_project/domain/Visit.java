package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "visit_table")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_id_seq_gen")
    @SequenceGenerator(name = "visit_id_seq_gen", sequenceName = "visits_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "date_of_visit")
    private Date dateOfVisit;

    @Column(name = "final_price")
    private double finalPrice;

    @Column(name = "customer_id")
    private int customerId;
}

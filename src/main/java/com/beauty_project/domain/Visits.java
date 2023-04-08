package com.beauty_project.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "visits")
public class Visits {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_id_seq_gen")
    @SequenceGenerator(name = "visit_id_seq_gen", sequenceName = "visits_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "date_of_visit")
    private Date dateOfVisit;

    @Column(name = "final_price")
    private int finalPrice;
}

package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "customer_table")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq_gen")
    @SequenceGenerator(name = "customer_id_seq_gen", sequenceName = "customer_table_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String customerName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "instagram_account")
    private String instagramAccount;

    @Column(name = "status")
    private String status;
}

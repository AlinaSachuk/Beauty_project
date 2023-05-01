package com.beauty_project.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Pattern(regexp = "[0-9]{11}")
    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "instagram_account")
    private String instagramAccount;

    @Column(name = "status")
    private String status;

    @Size(min = 5)
    @Column(name = "password")
    private String password;
}

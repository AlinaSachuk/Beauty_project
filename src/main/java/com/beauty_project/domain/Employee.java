package com.beauty_project.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee_table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq_gen")
    @SequenceGenerator(name = "employee_id_seq_gen", sequenceName = "employee_table_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "position")
    private String position;

    @Column(name = "education")
    private String education;

    @Column(name = "work_experience")
    private String workExperience;
}

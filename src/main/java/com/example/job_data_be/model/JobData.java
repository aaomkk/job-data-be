package com.example.job_data_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "job-data")
public class JobData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "employer")
    private String employer;

    @Column(name = "location")
    private String location;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "years_at_employer")
    private Double yearsAtEmployer;

    @Column(name = "years_of_experience")
    private Double yearsOfExperience;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "signing_bonus")
    private Double signingBonus;

    @Column(name = "annual_bonus")
    private Double annualBonus;

    @Column(name = "annual_stock_bonus")
    private Double annualStockBonus;

    @Column(name = "gender")
    private String gender;

    @Column(name = "additional_comments")
    private String additionalComments;

}


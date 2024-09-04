package com.example.job_data_be.repository;

import com.example.job_data_be.model.JobData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDataRepository extends JpaRepository<JobData, Long> { }

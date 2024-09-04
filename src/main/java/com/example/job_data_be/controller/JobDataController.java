package com.example.job_data_be.controller;

import com.example.job_data_be.model.JobData;
import com.example.job_data_be.service.JobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/job-data")
public class JobDataController {
    @Autowired
    private JobDataService jobDataService;

    @GetMapping
    public List<JobData> getJobDataList(
            @RequestParam(required = false) Double salary,
            @RequestParam(required = false) String gender,
            @RequestParam(name = "job_title", required = false) String jobTitle,
            @RequestParam(name = "salary_gte", required = false) Double salaryGte,
            @RequestParam(name = "salary_lte", required = false) Double salaryLte,
            @RequestParam(required = false) List<String> fields,
            @RequestParam(required = false) String sort,
            @RequestParam(name = "sort_type", required = false) String sortType)  {
        return jobDataService.getJobDataList(salary, jobTitle, gender, salaryGte, salaryLte, fields, sort, sortType);
    }
}

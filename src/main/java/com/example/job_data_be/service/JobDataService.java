package com.example.job_data_be.service;

import com.example.job_data_be.model.JobData;
import com.example.job_data_be.repository.JobDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobDataService {

    @Autowired
    private JobDataRepository jobDataRepository;

    public List<JobData> getJobDataList(
            Double salary,
            String jobTitle,
            String gender,
            Double salaryGte,
            Double salaryLte,
            List<String> fields,
            String sort,
            String sortType) {

        JobData jobExample = new JobData();
        
        if (salary != null) jobExample.setSalary(salary);
        if (jobTitle != null) jobExample.setJobTitle(jobTitle);
        if (gender != null) jobExample.setGender(gender);

        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        Example<JobData> example = Example.of(jobExample, matcher);

        List<JobData> result = jobDataRepository.findAll(example);

        if (salaryGte != null || salaryLte != null) {
            List<JobData> filteredResult = new ArrayList<>();
            for (JobData job : result) {
                boolean correct = true;
                if (job.getSalary() == null) {
                    continue;
                }
                if (salaryGte != null && job.getSalary() < salaryGte) {
                    correct = false;
                }
                if (salaryLte != null && job.getSalary() > salaryLte) {
                    correct = false;
                }
                if (correct) {
                    filteredResult.add(job);
                }
            }
            result = filteredResult;
        }

        if (sort != null) {
            this.sortByField(result, sort, sortType);
        }

        if (fields != null && !fields.isEmpty()) {
            List<JobData> filteredJobColumn = new ArrayList<>();
            for (JobData job : result) {
                JobData temp = new JobData();
                temp.setId(job.getId());
                if (fields.contains("salary")) temp.setSalary(job.getSalary());
                if (fields.contains("job_title")) temp.setJobTitle(job.getJobTitle());
                if (fields.contains("timestamp")) temp.setTimestamp(job.getTimestamp());
                if (fields.contains("employer")) temp.setEmployer(job.getEmployer());
                if (fields.contains("location")) temp.setLocation(job.getLocation());
                if (fields.contains("years_at_employer")) temp.setYearsAtEmployer(job.getYearsAtEmployer());
                if (fields.contains("years_of_experience")) temp.setYearsOfExperience(job.getYearsOfExperience());
                if (fields.contains("signing_bonus")) temp.setSigningBonus(job.getSigningBonus());
                if (fields.contains("annual_bonus")) temp.setAnnualBonus(job.getAnnualBonus());
                if (fields.contains("annual_stock_bonus")) temp.setAnnualStockBonus(job.getAnnualStockBonus());
                if (fields.contains("gender")) temp.setGender(job.getGender());
                if (fields.contains("additional_comments")) temp.setAdditionalComments(job.getAdditionalComments());
                filteredJobColumn.add(temp);
            }
            return filteredJobColumn;
        }
        return  result;
    }

    private void sortByField(List<JobData> data, String field, String sortType ) {
        Comparator<JobData> comparator = switch (field) {
            case "timestamp" -> Comparator.comparing(JobData::getTimestamp);
            case "employer" -> Comparator.comparing(JobData::getEmployer);
            case "location" -> Comparator.comparing(JobData::getLocation);
            case "job_title" -> Comparator.comparing(JobData::getJobTitle);
            case "years_at_employer" -> Comparator.comparingDouble(JobData::getYearsAtEmployer);
            case "years_of_experience" -> Comparator.comparingDouble(JobData::getYearsOfExperience);
            case "salary" -> Comparator.comparing(JobData::getSalary);
            case "annual_bonus" -> Comparator.comparing(JobData::getAnnualBonus);
            case "annual_stock_bonus" -> Comparator.comparing(JobData::getAnnualStockBonus);
            case "gender" -> Comparator.comparing(JobData::getGender);
            case "additional_comments" -> Comparator.comparing(JobData::getAdditionalComments);
            default -> Comparator.comparing(JobData::getId);
        };
        if (sortType != null && sortType.equalsIgnoreCase("DESC")) {
            comparator = comparator.reversed();
        }
        data.sort(comparator);
    }
}
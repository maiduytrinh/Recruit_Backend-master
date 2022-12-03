package com.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.converter.JobConverter;
import com.app.dto.request.PaginationRequest;
import com.app.dto.response.JobResponseType;
import com.app.entities.Job;
import com.app.exception.NotFoundEntityException;
import com.app.repository.JobRepository;
import com.app.service.JobService;
import com.app.ultils.Constraints;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobConverter jobConverter;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobConverter jobConverter){
        this.jobConverter = jobConverter;
        this.jobRepository = jobRepository;
    }

    @Override
    public JobResponseType save(JobResponseType jobResponseType) throws Exception {
        // TODO Auto-generated method stub
        JobResponseType response = null;
        Job job = jobConverter.ConvertToEntity(jobResponseType);
        Job jobSave = jobRepository.save(job);
        if (null != jobSave) {
            response = jobConverter.ConvertToDTO(jobSave);
        }
        return response;
    }

    @Override
    public JobResponseType findById(Integer id) {
        // TODO Auto-generated method stub
        Optional<Job> job= jobRepository.findById(id);
        if (job.isPresent()){
            return jobConverter.ConvertToAll(job.get());
            
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public JobResponseType update(JobResponseType jobResponseType, Integer id) throws Exception {
        // TODO Auto-generated method stub
        Job job = null;
        Optional<Job> jobOptional = Optional.ofNullable(jobRepository.findById(id).orElse(null));
        Job jobUpdate = jobConverter.ConvertToEntity(jobResponseType);
        if (jobOptional.isPresent()) {
            job = jobOptional.get();
            job.setName(jobUpdate.getName());
            Job jobSave = jobRepository.save(job);
            return jobConverter.ConvertToAll(jobSave);
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public Map<String, Object> paginationJob(PaginationRequest request) {
        // TODO Auto-generated method stub
        Pageable pageable = null;
        Map<String, Object> result = new HashMap<>();
        if (request.getPage() > 0) {
            pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        }
        if (request.getOrder().equals("asc")) {
            pageable = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getField()).ascending());
        }
        if (request.getOrder().equals("desc")) {
            pageable = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getField()).descending());
        }
        Page<Job> jobPage = jobRepository.paginationJob(pageable, request.getSearch());
        List<JobResponseType> companyResponseTypes = jobPage.toList().stream().map(job -> jobConverter.ConvertToDTO(job)).collect(Collectors.toList());
        result.put("jobs", companyResponseTypes);
        result.put("totalPages", jobPage.getTotalPages());
        result.put("totalElements", jobPage.getTotalElements());
        result.put("currentPage", request.getPage());
        return result;
    }
    
}

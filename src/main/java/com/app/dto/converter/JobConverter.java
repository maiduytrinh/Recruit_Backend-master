package com.app.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.response.JobResponseType;
import com.app.dto.response.ListJobResponseType;
import com.app.entities.Job;
import com.app.entities.ListJobs;

@Component
public class JobConverter {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ListJobConverter listJobConverter;
    
    public JobResponseType ConvertToBasic(Job job){
        JobResponseType response = new JobResponseType();
        response.setId(job.getId());
        response.setName(job.getName());
        return response;
    }

    public JobResponseType ConvertToDTO(Job job){
        JobResponseType response = new JobResponseType();
        response.setId(job.getId());
        response.setName(job.getName());
        return response;
    }

    public JobResponseType ConvertToAll(Job job){
        JobResponseType response = new JobResponseType();
        response.setId(job.getId());
        response.setName(job.getName());
        List<ListJobResponseType> listJobResponseTypes = new ArrayList<>();
        List<ListJobs> listJobs = job.getListJobs();
        if(null != listJobs){
            listJobs.forEach(item -> {
                ListJobResponseType listJobResponseType = listJobConverter.ConvertToBasic(item);
                listJobResponseTypes.add(listJobResponseType);
            });
        }
        response.setListJobs(listJobResponseTypes);
        return response;
    }

    public Job ConvertToEntity(JobResponseType jobResponseType){
        Job job = modelMapper.map(jobResponseType, Job.class);
        return job;
    }
}

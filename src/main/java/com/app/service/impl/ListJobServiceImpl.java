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

import com.app.dto.converter.ListJobConverter;
import com.app.dto.request.PaginationRequest;
import com.app.dto.response.ListJobResponseType;
import com.app.entities.ListJobs;
import com.app.exception.NotFoundEntityException;
import com.app.repository.ListJobRepository;
import com.app.service.ListJobService;
import com.app.ultils.Constraints;

@Service
public class ListJobServiceImpl implements ListJobService{
    private final ListJobConverter listJobConverter;
    private final ListJobRepository listJobRepository;
    @Autowired
    public ListJobServiceImpl(ListJobConverter listJobConverter, ListJobRepository listJobRepository){
        this.listJobConverter = listJobConverter;
        this.listJobRepository = listJobRepository;
    }

    @Override
    public ListJobResponseType save(ListJobResponseType listJobResponseType) throws Exception {
        // TODO Auto-generated method stub
        ListJobResponseType response = null;
        ListJobs job = listJobConverter.ConvertoEntity(listJobResponseType);
        ListJobs jobSave = listJobRepository.save(job);
        if (null != jobSave) {
            response = listJobConverter.ConvertToDTO(jobSave);
        }
        return response;
    }

    @Override
    public ListJobResponseType update(ListJobResponseType listJobResponseType, Integer id) throws Exception {
        // TODO Auto-generated method stub
        ListJobs job = null;
        Optional<ListJobs> jobOptional = Optional.ofNullable(listJobRepository.findById(id).orElse(null));
        ListJobs jobUpdate = listJobConverter.ConvertoEntity(listJobResponseType);
        if (jobOptional.isPresent()) {
            job = jobOptional.get();
            job.setName(jobUpdate.getName());
            job.setAge(jobUpdate.getAge());
            job.setArea(jobUpdate.getArea());
            job.setCertification(jobUpdate.getCertification());
            job.setContactInfo(jobUpdate.getContactInfo());
            job.setDescription(jobUpdate.getDescription());
            job.setExperence(jobUpdate.getExperence());
            job.setQuantity(jobUpdate.getQuantity());
            job.setSalary(jobUpdate.getSalary());
            job.setSex(jobUpdate.getSex());
            job.setWorkAddress(jobUpdate.getWorkAddress());
            if(null != jobUpdate.getJob()){
                job.setJob(jobUpdate.getJob());
            }
            ListJobs jobSave = listJobRepository.save(job);
            return listJobConverter.ConvertToDTO(jobSave);
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public ListJobResponseType findById(Integer id) {
        // TODO Auto-generated method stub
        Optional<ListJobs> job= listJobRepository.findById(id);
        if (job.isPresent()){
            return listJobConverter.ConvertToAll(job.get());
            
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        Optional<ListJobs> jobOptional = listJobRepository.findById(id);
        if (jobOptional.isPresent()) {
            listJobRepository.deleteById(id);
            return true;
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public Map<String, Object> paginationListJob(PaginationRequest request) {
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
        Page<ListJobs> listJobPage = listJobRepository.paginationListJob(pageable, request.getSearch());
        List<ListJobResponseType> listJobResponseTypes = listJobPage.toList().stream().map(job -> listJobConverter.ConvertToDTO(job)).collect(Collectors.toList());
        result.put("jobs", listJobResponseTypes);
        result.put("totalPages", listJobPage.getTotalPages());
        result.put("totalElements", listJobPage.getTotalElements());
        result.put("currentPage", request.getPage());
        return result;
    }

    
}

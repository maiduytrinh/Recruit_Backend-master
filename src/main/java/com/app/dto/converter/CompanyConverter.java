package com.app.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.response.CompanyResponseType;
import com.app.dto.response.ListJobResponseType;
import com.app.entities.Company;
import com.app.entities.ListJobs;

@Component
public class CompanyConverter{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ListJobConverter listJobConverter;

    public CompanyResponseType ConvertToDTO(Company company){
        CompanyResponseType response = new CompanyResponseType();
        response.setAddress(company.getAddress());
        response.setEmail(company.getEmail());
        response.setId(company.getId());
        response.setName(company.getName());
        response.setPhone(company.getPhone());
        return response;
    }

    public CompanyResponseType ConvertToAll(Company company){
        CompanyResponseType response = new CompanyResponseType();
        response.setAddress(company.getAddress());
        response.setEmail(company.getEmail());
        response.setId(company.getId());
        response.setName(company.getName());
        response.setPhone(company.getPhone());
        List<ListJobResponseType> listJobResponseTypes = new ArrayList<>();
        List<ListJobs> jobs = company.getJobs();
        if(null != jobs){
            jobs.forEach(item -> {
                ListJobResponseType listJobResponseType = listJobConverter.ConvertToBasic(item);
                listJobResponseTypes.add(listJobResponseType);
            });
        }
        response.setJobs(listJobResponseTypes);
        return response;
    }

    public Company ConvertToEntity(CompanyResponseType companyResponseType){
        Company company = modelMapper.map(companyResponseType, Company.class);
        return company;
    }
}
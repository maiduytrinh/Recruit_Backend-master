package com.app.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.response.ListJobResponseType;
import com.app.dto.response.ListProfileResponseType;
import com.app.entities.ListJobs;
import com.app.entities.ListProfileId;
import com.app.entities.ListProfiles;

@Component
public class ListJobConverter {
    // private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserConverter userConverter;
    @Autowired
    JobConverter jobConverter;
    @Autowired
    CompanyConverter companyConverter;

    public ListJobResponseType ConvertToBasic(ListJobs listJobs){
        ListJobResponseType response = new ListJobResponseType();
        response.setName(listJobs.getName());
        response.setAge(listJobs.getAge());
        response.setArea(listJobs.getArea());
        response.setCertification(listJobs.getCertification());
        response.setContactInfo(listJobs.getContactInfo());
        response.setDescription(listJobs.getDescription());
        response.setExperence(listJobs.getExperence());
        response.setId(listJobs.getId());
        response.setQuantity(listJobs.getQuantity());
        response.setSalary(listJobs.getSalary());
        response.setSex(listJobs.getSex());
        response.setWorkAddress(listJobs.getWorkAddress());
        return response;
    }

    public ListJobResponseType ConvertToDTO(ListJobs listJobs){
        ListJobResponseType response = new ListJobResponseType();
        response.setName(listJobs.getName());
        response.setAge(listJobs.getAge());
        response.setArea(listJobs.getArea());
        response.setCertification(listJobs.getCertification());
        response.setContactInfo(listJobs.getContactInfo());
        response.setDescription(listJobs.getDescription());
        response.setExperence(listJobs.getExperence());
        response.setId(listJobs.getId());
        response.setQuantity(listJobs.getQuantity());
        response.setSalary(listJobs.getSalary());
        response.setSex(listJobs.getSex());
        response.setWorkAddress(listJobs.getWorkAddress());
        if(null != listJobs.getJob()){
            response.setJob(jobConverter.ConvertToDTO(listJobs.getJob()));
        }
        if(null != listJobs.getCompany()){
            response.setCompany(companyConverter.ConvertToDTO(listJobs.getCompany()));
        }
        return response;
    }

    public ListJobResponseType ConvertToAll(ListJobs listJobs){
        ListJobResponseType response = new ListJobResponseType();
        response.setName(listJobs.getName());
        response.setAge(listJobs.getAge());
        response.setArea(listJobs.getArea());
        response.setCertification(listJobs.getCertification());
        response.setContactInfo(listJobs.getContactInfo());
        response.setDescription(listJobs.getDescription());
        response.setExperence(listJobs.getExperence());
        response.setId(listJobs.getId());
        response.setQuantity(listJobs.getQuantity());
        response.setSalary(listJobs.getSalary());
        response.setSex(listJobs.getSex());
        response.setWorkAddress(listJobs.getWorkAddress());
        if(null != listJobs.getJob()){
            response.setJob(jobConverter.ConvertToDTO(listJobs.getJob()));
        }
        if(null != listJobs.getCompany()){
            response.setCompany(companyConverter.ConvertToDTO(listJobs.getCompany()));
        }
        List<ListProfileResponseType> listProfileResponseTypes = new ArrayList<>();
        List<ListProfiles> listProfiles = listJobs.getListProfiles();
        if(null != listProfiles){
            listProfiles.forEach(item -> {
                 ListProfileResponseType listProfileResponseType = new ListProfileResponseType();
                 listProfileResponseType.setUsers(userConverter.ConvertToBasic(item.getListProfileId().getUsers()));
                 listProfileResponseTypes.add(listProfileResponseType);
            });
        }
        response.setListProfiles(listProfileResponseTypes);
        return response;
    }

    public ListJobs ConvertoEntity(ListJobResponseType listJobResponseType){
        ListJobs listJobs = new ListJobs();
        listJobs.setName(listJobResponseType.getName());
        listJobs.setAge(listJobResponseType.getAge());
        listJobs.setArea(listJobResponseType.getArea());
        listJobs.setCertification(listJobResponseType.getCertification());
        listJobs.setContactInfo(listJobResponseType.getContactInfo());
        listJobs.setDescription(listJobResponseType.getDescription());
        listJobs.setExperence(listJobResponseType.getExperence());
        listJobs.setId(listJobResponseType.getId());
        listJobs.setQuantity(listJobResponseType.getQuantity());
        listJobs.setSalary(listJobResponseType.getSalary());
        listJobs.setSex(listJobResponseType.getSex());
        listJobs.setWorkAddress(listJobResponseType.getWorkAddress());
        if(null != listJobResponseType.getJob()){
            listJobs.setJob(jobConverter.ConvertToEntity(listJobResponseType.getJob()));
        }
        // ListJobs listJobs = modelMapper.map(listJobResponseType, ListJobs.class);
        return listJobs;
    }

}

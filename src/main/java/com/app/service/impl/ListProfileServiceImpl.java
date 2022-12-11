package com.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.converter.ListJobConverter;
import com.app.dto.converter.ListProfileConverter;
import com.app.dto.request.ListProfileRequest;
import com.app.dto.response.ListJobResponseType;
import com.app.dto.response.ListProfileResponseType;
import com.app.dto.response.UserResponseType;
import com.app.entities.Job;
import com.app.entities.ListJobs;
import com.app.entities.ListProfiles;
import com.app.exception.NotFoundEntityException;
import com.app.repository.ListJobRepository;
import com.app.repository.ListProfileReponsitory;
import com.app.service.ListProfileService;
import com.app.ultils.Constraints;

@Service
public class ListProfileServiceImpl implements ListProfileService{
    @Autowired
    ListProfileConverter listProfileConverter;
    @Autowired
    ListProfileReponsitory listProfileReponsitory;
    @Autowired
    ListJobConverter listJobConverter;
    @Autowired
    ListJobRepository listJobRepository;  

    @Override
    public ListProfileResponseType save(ListProfileRequest listProfileRequest){
        // TODO Auto-generated method stub
        ListProfileResponseType response = new ListProfileResponseType();
        try {
            Integer isInsert = listProfileReponsitory.AddListProfile(listProfileRequest.getIdUser(), listProfileRequest.getIdJob());
            if(isInsert == 1){
                ListJobs listJobs = listJobRepository.getById(listProfileRequest.getIdJob());
                response.setJobs(listJobConverter.ConvertToDTO(listJobs));
                response.setMessage("Ứng tuyển thành công");
                return response;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        response.setMessage("Ứng tuyển không thành công");
        return response;   
    }

    @Override
    public Boolean delete(ListProfileRequest listProfileRequest) {
        // TODO Auto-generated method stub
        int Del = listProfileReponsitory.DelListProfile(listProfileRequest.getIdUser(), listProfileRequest.getIdUser());
        if(Del == 1){
            return true;
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }
    
}

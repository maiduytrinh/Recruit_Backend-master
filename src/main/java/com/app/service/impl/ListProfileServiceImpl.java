package com.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.converter.ListProfileConverter;
import com.app.dto.response.ListProfileResponseType;
import com.app.entities.ListProfiles;
import com.app.exception.NotFoundEntityException;
import com.app.repository.ListProfileReponsitory;
import com.app.service.ListProfileService;
import com.app.ultils.Constraints;

@Service
public class ListProfileServiceImpl implements ListProfileService{
    @Autowired
    ListProfileConverter listProfileConverter;
    @Autowired
    ListProfileReponsitory listProfileReponsitory;

    @Override
    public Boolean save(ListProfileResponseType listProfileResponseType){
        // TODO Auto-generated method stub
        Boolean response = false;
        ListProfiles listProfile = listProfileConverter.ConvertToEntity(listProfileResponseType);
        try {
            int Insert = listProfileReponsitory.AddListProfile(listProfile.getListProfileId().getUsers().getId(), listProfile.getListProfileId().getJobs().getId());
            if (Insert == 1) {
                response = true;
            }
        } catch (Exception e) {
        }
        return response;
        
        
    }

    @Override
    public Boolean delete(ListProfileResponseType listProfileResponseType) {
        // TODO Auto-generated method stub
        ListProfiles listProfileDel = listProfileConverter.ConvertToEntity(listProfileResponseType);
        int Del = listProfileReponsitory.DelListProfile(listProfileDel.getListProfileId().getUsers().getId(), listProfileDel.getListProfileId().getJobs().getId());
        if(Del == 1){
            return true;
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }
    
}

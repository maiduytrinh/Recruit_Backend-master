package com.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.response.ProfileResponseType;
import com.app.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Override
    public ProfileResponseType save(ProfileResponseType t) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfileResponseType update(ProfileResponseType t, Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfileResponseType findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ProfileResponseType> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
}

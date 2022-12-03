package com.app.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.app.dto.response.ProfileResponseType;
import com.app.entities.Profile;

@Component
public class ProfileConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public ProfileResponseType convertToDTO(Profile profile){
        ProfileResponseType response = new ProfileResponseType();
        response.setAddress(profile.getAddress());
        response.setCCCD(profile.getCCCD());
        response.setCareer(profile.getCareer());
        response.setEducation(profile.getEducation());
        response.setExperence(profile.getExperence());
        response.setHeight(profile.getHeight());
        response.setHobby(profile.getHobby());
        response.setHomeId(profile.getHomeId());
        response.setId(profile.getId());
        response.setJobInformation(profile.getJobInformation());
        response.setPersonality(profile.getPersonality());
        response.setPhone(profile.getPhone());
        response.setSalary(profile.getSalary());
        response.setSchoolName(profile.getSchoolName());
        response.setWeight(profile.getWeight());
        return response;
    }

    public Profile convertToEntity(ProfileResponseType profileResponseType){
        Profile profile = modelMapper.map(profileResponseType, Profile.class);
        return profile;
    }
}

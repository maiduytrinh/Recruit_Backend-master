package com.app.dto.converter;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.response.ListProfileResponseType;
import com.app.dto.response.UserResponseType;
import com.app.entities.ListProfiles;
import com.app.entities.Users;

@Component
public class UserConverter {
    @Autowired
    ProfileConverter profileConverter;
    @Autowired
    ListJobConverter listJobConverter;
    @Autowired
    RoleConverter roleConverter;
    

    public UserResponseType ConvertToBasic(Users users){
        UserResponseType response = new UserResponseType();
        response.setId(users.getId());
        response.setFullName(users.getFullName());
        response.setBirthDay(users.getBirthDay());
        response.setUuid(users.getUuid());
        response.setEmail(users.getEmail());
        response.setSex(users.getSex());
        response.setUrlImg(users.getUrlImg());
        if(null != users.getRoles()){
            response.setRoles(roleConverter.ConvertToDTO(users.getRoles()));
        }
        return response;
    }

    public UserResponseType ConvertToDTO(Users users){
        UserResponseType response = new UserResponseType();
        response.setId(users.getId());
        response.setFullName(users.getFullName());
        response.setBirthDay(users.getBirthDay());
        response.setUuid(users.getUuid());
        response.setEmail(users.getEmail());
        response.setSex(users.getSex());
        response.setUrlImg(users.getUrlImg());
        if(null != users.getProfile()){
            response.setProfile(profileConverter.convertToDTO(users.getProfile()));
        }
        if(null != users.getRoles()){
            response.setRoles(roleConverter.ConvertToDTO(users.getRoles()));
        }
        return response;
    }

    public UserResponseType ConvertToAll(Users users){
        UserResponseType response = new UserResponseType();
        response.setId(users.getId());
        response.setFullName(users.getFullName());
        response.setBirthDay(users.getBirthDay());
        response.setUuid(users.getUuid());
        response.setEmail(users.getEmail());
        response.setSex(users.getSex());
        response.setUrlImg(users.getUrlImg());
        if(null != users.getProfile()){
            response.setProfile(profileConverter.convertToDTO(users.getProfile()));
        }
        if(null != users.getRoles()){
            response.setRoles(roleConverter.ConvertToDTO(users.getRoles()));
        }
        List<ListProfileResponseType> listProfileResponseTypes = new ArrayList<>();
        List<ListProfiles> listProfiles = users.getListJobs();
        if(null != listProfiles){
            listProfiles.forEach(item -> {
                 ListProfileResponseType listProfileResponseType = new ListProfileResponseType();
                 listProfileResponseType.setJobs(listJobConverter.ConvertToDTO(item.getListProfileId().getJobs()));
                 listProfileResponseTypes.add(listProfileResponseType);
            });
        }
        response.setListJobs(listProfileResponseTypes);
        return response;
    }

    public Users convertToEntity(UserResponseType userResponseType){
        // Users users = modelMapper.map(userResponseType, Users.class);

        Users users = new Users();
        users.setId(userResponseType.getId());
        users.setFullName(userResponseType.getFullName());
        users.setBirthDay(userResponseType.getBirthDay());
        users.setUuid(userResponseType.getUuid());
        users.setEmail(userResponseType.getEmail());
        users.setSex(userResponseType.getSex());
        users.setUrlImg(userResponseType.getUrlImg());
        users.setPasssword(userResponseType.getPasssword());
        if(null != userResponseType.getProfile()){
            users.setProfile(profileConverter.convertToEntity(userResponseType.getProfile()));
        }
        // if(null != users.getRoles()){
        //     users.setRoles(roleConverter.con(users.getRoles()));
        // }
        // List<ListProfiles> listProfiles = new ArrayList<>();
        // List<ListProfileResponseType> listProfileResponseTypes = userResponseType.getListProfiles();
        // if(null != listProfileResponseTypes){
        //     listProfileResponseTypes.forEach(item -> {
        //          ListProfileId listProfileId = new ListProfileId();
        //          listProfileId.setUsers(users);
        //          listProfileId.setJobs(listJobConverter.ConvertoEntity(item.getJobs()));
        //          ListProfiles listProfile = new ListProfiles(listProfileId);
        //          listProfiles.add(listProfile);
        //     });
        // }
        // users.setListProfiles(listProfiles);
        return users;
    }
}

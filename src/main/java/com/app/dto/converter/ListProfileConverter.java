package com.app.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.response.ListProfileResponseType;
import com.app.entities.ListJobs;
import com.app.entities.ListProfileId;
import com.app.entities.ListProfiles;
import com.app.entities.Users;

@Component
public class ListProfileConverter {
    private final UserConverter userConverter;
    private final ListJobConverter listJobConverter;

    @Autowired
    public ListProfileConverter(UserConverter userConverter, ListJobConverter listJobConverter){
        this.userConverter = userConverter;
        this.listJobConverter = listJobConverter;
    }

    public ListProfileResponseType ConvertToDTO(ListProfiles listProfiles){
        ListProfileResponseType listProfileResponseType = new ListProfileResponseType();
        Users users = listProfiles.getListProfileId().getUsers();
        ListJobs listJobs = listProfiles.getListProfileId().getJobs();
        if(null != users){
            listProfileResponseType.setUsers(userConverter.ConvertToBasic(users));
        }
        if(null != listJobs){
            listProfileResponseType.setJob(listJobConverter.ConvertToDTO(listJobs));
        }
        listProfileResponseType.setStatus(listProfiles.getStatus());
        return listProfileResponseType;
    }
}

package com.app.dto.converter;

import org.springframework.stereotype.Component;

import com.app.dto.response.RoleResponseType;
import com.app.entities.Roles;

@Component
public class RoleConverter {
    public RoleResponseType ConvertToDTO(Roles roles){
        RoleResponseType response = new RoleResponseType();
        response.setId(roles.getId());
        response.setRoleName(roles.getRoleName());
        return response;
    }
}

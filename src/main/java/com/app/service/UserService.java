package com.app.service;

import com.app.dto.response.UserResponseType;

public interface UserService extends BaseService<UserResponseType, Integer>{
    UserResponseType loadUserByEmail(String email);
}

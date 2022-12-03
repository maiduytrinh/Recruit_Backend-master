package com.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProfileResponseType {
    private Integer id;
    private Integer height;
    private Integer weight;
    private Integer experence;
    private String schoolName;
    private String homeId;
    private String CCCD;
    private String hobby;
    private String personality;
    private String address;
    private String education;
    private String career;
    private String salary;
    private String jobInformation;
    private String phone;
    private UserResponseType userResponseType;
}

package com.app.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseType {
    private Integer id;
    private LocalDate birthDay;
    private String fullName;
    private Boolean sex;
    private String urlImg;
    private String uuid;
    private String email;
    private String passsword;
    private RoleResponseType roles;
    private ProfileResponseType profile;
    private List<ListProfileResponseType> listJobs;
}

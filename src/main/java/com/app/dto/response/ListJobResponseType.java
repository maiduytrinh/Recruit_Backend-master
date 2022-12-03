package com.app.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListJobResponseType {
    private Integer id;
    private String name;
    private String salary;
    private Integer quantity;
    private Boolean sex;
    private String age;
    private String certification;
    private String experence;
    private String contactInfo;
    private String area;
    private String workAddress;
    private String description;
    private JobResponseType job;
    private CompanyResponseType company;
    private List<ListProfileResponseType> listProfiles;
}

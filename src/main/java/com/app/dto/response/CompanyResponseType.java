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

public class CompanyResponseType {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<ListJobResponseType> jobs;
}

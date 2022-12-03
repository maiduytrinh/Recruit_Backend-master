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

public class JobResponseType {
    private Integer id;
    private String name;
    private List<ListJobResponseType> listJobs;
}

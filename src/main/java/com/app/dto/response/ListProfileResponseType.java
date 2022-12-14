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

public class ListProfileResponseType {
    private String message;
    private UserResponseType users;
    private ListJobResponseType job;
    private Integer status;
}

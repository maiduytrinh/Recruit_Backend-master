package com.app.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ListProfileRequest {
    @NotBlank
    private Integer idUser;
    @NotBlank
    private Integer idJob;
}

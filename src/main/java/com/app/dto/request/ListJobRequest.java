package com.app.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ListJobRequest {
    private Integer codeAddress;
    private Integer idJob;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Long salaryStart;
    private Long salaryEnd;
}

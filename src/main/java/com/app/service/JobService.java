package com.app.service;

import java.util.Map;

import com.app.dto.request.PaginationRequest;
import com.app.dto.response.JobResponseType;

public interface JobService extends BaseService<JobResponseType, Integer>{
    Map<String, Object> paginationJob(PaginationRequest request);
}

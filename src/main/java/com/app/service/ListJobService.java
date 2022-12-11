package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.dto.request.ListJobRequest;
import com.app.dto.request.PaginationRequest;
import com.app.dto.response.ListJobResponseType;

public interface ListJobService extends BaseService<ListJobResponseType, Integer>{
    Map<String, Object> paginationListJob(PaginationRequest request);
    public List<ListJobResponseType> getJobs(ListJobRequest listJobRequest);
}

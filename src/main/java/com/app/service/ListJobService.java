package com.app.service;

import java.util.Map;

import com.app.dto.request.PaginationRequest;
import com.app.dto.response.ListJobResponseType;

public interface ListJobService extends BaseService<ListJobResponseType, Integer>{
    Map<String, Object> paginationListJob(PaginationRequest request);
}

package com.app.service;

import java.util.Map;

import com.app.dto.request.PaginationRequest;
import com.app.dto.response.CompanyResponseType;

public interface CompanyService extends BaseService<CompanyResponseType, Integer>{
    Map<String, Object> paginationCompany(PaginationRequest request);


}

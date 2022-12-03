package com.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.converter.CompanyConverter;
import com.app.dto.request.PaginationRequest;
import com.app.dto.response.CompanyResponseType;
import com.app.entities.Company;
import com.app.exception.NotFoundEntityException;
import com.app.repository.CompanyRepository;
import com.app.service.CompanyService;
import com.app.ultils.Constraints;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyConverter companyConverter){
        this.companyConverter = companyConverter;
        this.companyRepository = companyRepository;
    }


    @Override
    public CompanyResponseType save(CompanyResponseType companyResponseType) throws Exception {
        // TODO Auto-generated method stub
        CompanyResponseType response = null;
        Company company = companyConverter.ConvertToEntity(companyResponseType);
        Company companySave = companyRepository.save(company);
        if (null != companySave) {
            response = companyConverter.ConvertToDTO(companySave);
        }
        return response;
    }

    @Override
    public CompanyResponseType update(CompanyResponseType companyResponseType, Integer id) throws Exception{
        // TODO Auto-generated method stub
        Company company = null;
        Optional<Company> companyOptional = Optional.ofNullable(companyRepository.findById(id).orElse(null));
        Company companyUpdate = companyConverter.ConvertToEntity(companyResponseType);
        if (companyOptional.isPresent()) {
            company = companyOptional.get();
            company.setAddress(companyUpdate.getAddress());
            company.setEmail(companyUpdate.getEmail());
            company.setName(companyUpdate.getName());
            company.setPhone(companyUpdate.getPhone());
            Company companySave = companyRepository.save(company);
            return companyConverter.ConvertToDTO(companySave);
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public CompanyResponseType findById(Integer id) {
        Optional<Company> company= companyRepository.findById(id);
        if (company.isPresent()){
            return companyConverter.ConvertToAll(company.get());
            
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            companyRepository.deleteById(id);
            return true;
        }
        throw new NotFoundEntityException(Constraints.VALIDATE_NOT_FOUND);
    }

    @Override
    public Map<String, Object> paginationCompany(PaginationRequest request) {
        // TODO Auto-generated method stub
        Pageable pageable = null;
        Map<String, Object> result = new HashMap<>();
        if (request.getPage() > 0) {
            pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        }
        if (request.getOrder().equals("asc")) {
            pageable = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getField()).ascending());
        }
        if (request.getOrder().equals("desc")) {
            pageable = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getField()).descending());
        }
        Page<Company> companyPage = companyRepository.paginationCompany(pageable, request.getSearch());
        List<CompanyResponseType> companyResponseTypes = companyPage.toList().stream().map(company -> companyConverter.ConvertToDTO(company)).collect(Collectors.toList());
        result.put("companys", companyResponseTypes);
        result.put("totalPages", companyPage.getTotalPages());
        result.put("totalElements", companyPage.getTotalElements());
        result.put("currentPage", request.getPage());
        return result;
    }
    
}

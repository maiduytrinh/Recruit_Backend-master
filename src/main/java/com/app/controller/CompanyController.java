package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.request.PaginationRequest;
import com.app.dto.response.CompanyResponseType;
import com.app.service.CompanyService;
import com.app.service.impl.FileStorageServiceImpl;
import com.app.ultils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;
    private final FileStorageServiceImpl fileStorageService;

    @Autowired
    public CompanyController(CompanyService companyService, FileStorageServiceImpl fileStorageService) {
        this.companyService = companyService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationCompany(@RequestBody PaginationRequest paginationRequest) throws JsonProcessingException {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(companyService.paginationCompany(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseType> findById(@PathVariable(name = "id") Integer id) throws JsonProcessingException {
        ResponseEntity<CompanyResponseType> pResponse = null;
        CompanyResponseType companyResponseType = companyService.findById(id);
        
        pResponse = new ResponseEntity<>(companyResponseType, HttpStatus.OK);
        return pResponse;
    }

    @PostMapping("/save")
    public ResponseEntity<CompanyResponseType> save(@RequestParam("company") String companyJson,
                                                    @RequestParam(value = "image",required = false) MultipartFile image) throws Exception {
        String imgUrl = "";
        ResponseEntity<CompanyResponseType> pResponse;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        CompanyResponseType companyResponseType = mapper.readValue(companyJson,CompanyResponseType.class);
        if(null != image){
            imgUrl = fileStorageService.storeFile(image);
            companyResponseType.setUrlImg(Utils.getUrlFilePathImage(imgUrl));
        }
        pResponse = new ResponseEntity<>(companyService.save(companyResponseType), HttpStatus.CREATED);
        return pResponse;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CompanyResponseType> update(  @RequestBody CompanyResponseType companyResponseType, 
                                                        @PathVariable(name = "id") Integer id)
    throws Exception {
        CompanyResponseType response = companyService.update(companyResponseType, id);
        ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        boolean isDelete = companyService.delete(id);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.PaginationRequest;
import com.app.dto.response.ListJobResponseType;
import com.app.service.ListJobService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/listjobs")
public class ListJobController {
    private final ListJobService listJobService;
    @Autowired
    public ListJobController(ListJobService listJobService){
        this.listJobService = listJobService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationListJob(@RequestBody PaginationRequest paginationRequest) throws JsonProcessingException {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(listJobService.paginationListJob(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListJobResponseType> findById(@PathVariable(name = "id") Integer id) throws JsonProcessingException {
        ResponseEntity<ListJobResponseType> pResponse = null;
        ListJobResponseType jobResponseType = listJobService.findById(id);
        
        pResponse = new ResponseEntity<>(jobResponseType, HttpStatus.OK);
        return pResponse;
    }

    @PostMapping("/save")
    public ResponseEntity<ListJobResponseType> save(@RequestBody ListJobResponseType listJobResponseType) throws Exception {
        ListJobResponseType response = listJobService.save(listJobResponseType);
        ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ListJobResponseType> update(  @RequestBody ListJobResponseType jobResponseType, 
                                                        @PathVariable(name = "id") Integer id)
    throws Exception {
        ListJobResponseType response = listJobService.update(jobResponseType, id);
        ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        boolean isDelete = listJobService.delete(id);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}

package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.PaginationRequest;
import com.app.dto.response.JobResponseType;
import com.app.service.JobService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationJob(@RequestBody PaginationRequest paginationRequest) throws JsonProcessingException {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(jobService.paginationJob(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseType> findById(@PathVariable(name = "id") Integer id) throws JsonProcessingException {
        ResponseEntity<JobResponseType> pResponse = null;
        JobResponseType jobResponseType = jobService.findById(id);
        
        pResponse = new ResponseEntity<>(jobResponseType, HttpStatus.OK);
        return pResponse;
    }

    @PostMapping("/save")
    public ResponseEntity<JobResponseType> save(@RequestBody JobResponseType jobResponseType) throws Exception {
        JobResponseType response = jobService.save(jobResponseType);
        ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<JobResponseType> update(  @RequestBody JobResponseType jobResponseType, 
                                                        @PathVariable(name = "id") Integer id)
    throws Exception {
        JobResponseType response = jobService.update(jobResponseType, id);
        ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        boolean isDelete = jobService.delete(id);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}

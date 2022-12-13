package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.ListJobRequest;
import com.app.dto.request.PaginationRequest;
import com.app.dto.response.JobResponseType;
import com.app.dto.response.ListJobResponseType;
import com.app.entities.Job;
import com.app.service.JobService;
import com.app.service.ListJobService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/listjobs")
public class ListJobController {
    private final JobService jobService;
    private final ListJobService listJobService;
    @Autowired
    public ListJobController(ListJobService listJobService, JobService jobService){
        this.listJobService = listJobService;
        this.jobService = jobService;
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

    @PostMapping("/getjobs")
    public ResponseEntity<List<ListJobResponseType>> getListJob(@RequestBody ListJobRequest listJobRequest){
        ResponseEntity<List<ListJobResponseType>> response = null;
        response = new ResponseEntity<List<ListJobResponseType>>(listJobService.getJobs(listJobRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("/getnew")
    public ResponseEntity<List<ListJobResponseType>> getNew(){
        ResponseEntity<List<ListJobResponseType>> response = null;
        response = new ResponseEntity<List<ListJobResponseType>>(listJobService.getJobNew(), HttpStatus.OK);
        return response;
    }

    @GetMapping("/gethot")
    public ResponseEntity<List<ListJobResponseType>> getHot(){
        ResponseEntity<List<ListJobResponseType>> response = null;
        response = new ResponseEntity<List<ListJobResponseType>>(listJobService.getJobHot(), HttpStatus.OK);
        return response;
    }
}

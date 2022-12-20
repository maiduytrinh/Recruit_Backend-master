package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.ListProfileRequest;
import com.app.dto.response.ListJobResponseType;
import com.app.dto.response.ListProfileResponseSaveType;
import com.app.dto.response.ListProfileResponseType;
import com.app.service.ListProfileService;

@RestController
@RequestMapping("/api/listprofile")
public class ListProfileController {
    private final ListProfileService listProfileService;
    @Autowired
    public ListProfileController(ListProfileService listProfileService){
        this.listProfileService = listProfileService;
    }

    @PostMapping("/save")
    public ResponseEntity<ListProfileResponseSaveType> save(@RequestBody ListProfileRequest listProfileRequest) throws Exception {
        ListProfileResponseSaveType responese = listProfileService.save(listProfileRequest);
        if (responese.getJobs() != null) {
            return new ResponseEntity<>(responese, HttpStatus.CREATED);
        }
        
        return new ResponseEntity<>(responese, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody ListProfileRequest listProfileRequest) throws Exception {

        boolean isDelete = listProfileService.delete(listProfileRequest);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}

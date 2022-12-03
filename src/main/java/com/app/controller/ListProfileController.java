package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<HttpStatus> save(@RequestBody ListProfileResponseType listProfileResponseType) throws Exception {
        boolean isInsert = listProfileService.save(listProfileResponseType);
        if (isInsert) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody ListProfileResponseType listProfileResponseType) throws Exception {
        boolean isDelete = listProfileService.delete(listProfileResponseType);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}

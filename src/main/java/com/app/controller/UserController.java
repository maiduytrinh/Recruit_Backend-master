package com.app.controller;

import com.app.dto.response.UserResponseType;
import com.app.service.UserService;
import com.app.service.impl.FileStorageServiceImpl;
import com.app.ultils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private  FileStorageServiceImpl fileStorageService;
    @Autowired
    public UserController(UserService userService, FileStorageServiceImpl fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponseType> saveUser(@RequestParam("user") String userJson,
                                                     @RequestParam(value = "image",required = false)MultipartFile image) throws Exception {
        String imageUrl = "";
        ResponseEntity<UserResponseType> pResponse;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        UserResponseType userResponseType = mapper.readValue(userJson,UserResponseType.class);
        if(null != image){
            imageUrl = fileStorageService.storeFile(image);
            userResponseType.setUrlImg(Utils.getUrlFilePathImage(imageUrl));
        }
        pResponse = new ResponseEntity<>( userService.save(userResponseType), HttpStatus.OK);
        return pResponse;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseType> findByEmail(@PathVariable("email") String email) throws JsonProcessingException {
        ResponseEntity<UserResponseType> pResponse = new ResponseEntity<>(userService.loadUserByEmail(email), HttpStatus.OK);
        return pResponse;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserResponseType> update( @RequestParam("user") String userJson,
                                                    @RequestParam(value = "image", required = false) MultipartFile image, 
                                                    @PathVariable(name = "id") Integer id)
    throws Exception {
        ResponseEntity<UserResponseType> pResponse = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        UserResponseType userResponseType = objectMapper.readValue(userJson, UserResponseType.class);
        if (null != image) {
            String fileSave = fileStorageService.storeFile(image);
            userResponseType.setUrlImg(Utils.getUrlFilePathImage(fileSave));
        }
        UserResponseType response = userService.update(userResponseType, id);
        pResponse = new ResponseEntity<>(response, HttpStatus.OK);
        return pResponse;
    }
    
    // @PostMapping("/register")
    // public ResponseEntity<UserResponseType> registerUser(@RequestParam("user") String userJson) throws JsonProcessingException {
    //     ResponseEntity<UserResponseType> pResponse;
    //     ObjectMapper mapper = new ObjectMapper();
    //     mapper.registerModule(new JavaTimeModule());
    //     UserResponseType userResponseType = mapper.readValue(userJson,UserResponseType.class);
    //     pResponse = new ResponseEntity<>( userService.saveUser(userResponseType), HttpStatus.OK);
    //     return pResponse;
    // }
}

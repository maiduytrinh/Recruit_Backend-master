package com.app.controller;

import com.app.dto.response.LoginResponseType;
import com.app.dto.response.UserResponseType;
import com.app.security.CustomUserDetails;
import com.app.security.JwtTokenProvider;
import com.app.security.LoginRequest;
import com.app.service.UserService;
import com.app.service.impl.FileStorageServiceImpl;
import com.app.ultils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    private UserService userService;
    private  FileStorageServiceImpl fileStorageService;
    @Autowired
    public UserController(UserService userService, FileStorageServiceImpl fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/login")
    public LoginResponseType authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        LoginResponseType loginResponseType = new LoginResponseType();
        loginResponseType.setAccessToken(jwt);
        loginResponseType.setUser(userService.loadUserByEmail(loginRequest.getUsername()));
        return loginResponseType;
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

    // @GetMapping("/{email}")
    // public ResponseEntity<UserResponseType> findByEmail(@PathVariable("email") String email) throws JsonProcessingException {
    //     ResponseEntity<UserResponseType> pResponse = new ResponseEntity<>(userService.loadUserByEmail(email), HttpStatus.OK);
    //     return pResponse;
    // }

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

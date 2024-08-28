package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nus.iss.tasktracker.util.CustomResponseHandler;

import java.util.List;

@RestController
@Slf4j
public class UserRegistrationController {

    //private final UserRegistrationService userRegistrationService;

 /*   @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }*/
/*
    @GetMapping("user")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public UserDTO getUserById(@RequestParam (name = "id", defaultValue = "2019") int id){
        log.info("user endpoint called with id {}", id);
        return userRegistrationService.getUserById(id);
    }

    @PostMapping(value = "/change-password")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public ResponseEntity<Response> changePassword(@RequestBody UserDTO requestDTO) throws RuntimeException {
        userRegistrationService.changePassword(requestDTO);
        Object responseBody = null;// Your success response body
        HttpStatus status = HttpStatus.OK;
        String successMessage = "Password updated successfully.";
        return CustomResponseHandler.handleSuccessResponse(responseBody, status, successMessage);
    }

    @PostMapping(value = "/signup")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public ResponseEntity<Response> signUp(@RequestBody UserDTO requestDTO) throws RuntimeException {
        UserDTO userDTO=null;
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        try {
            userDTO=userRegistrationService.signUp(requestDTO);
        } catch(Exception e) {
            successOrFailMessage = e.getMessage();
        }

        log.info("userDTO : {}", userDTO);

        if (userDTO !=  null){
            responseBody = userDTO;
            successOrFailMessage = "SignUp successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }

    @GetMapping("userlist")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public List<UserDTO> getAllUsersForGroup(){
        log.info("userlist endpoint called");
        return userRegistrationService.getAllUsersInAGroup();
    }

    @PostMapping(value = "/userupdate")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public ResponseEntity<Response> updateUserInfo(@RequestBody UserDTO requestDTO) throws RuntimeException {
        UserDTO userDTO=null;
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        try {
            userDTO=userRegistrationService.updateUserInfo(requestDTO);
        } catch(Exception e) {
            successOrFailMessage = e.getMessage();
        }

        log.info("userDTO : {}", userDTO);

        if (userDTO !=  null){
            responseBody = userDTO;
            successOrFailMessage = "User Details Updated successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }*/

}

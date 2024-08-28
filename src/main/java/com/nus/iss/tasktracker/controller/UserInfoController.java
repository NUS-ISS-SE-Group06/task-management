package com.nus.iss.tasktracker.controller;


import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.UserInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
import com.nus.iss.tasktracker.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/userinfo")
@Slf4j
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final JWTUtil jwtUtil;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, JWTUtil jwtUtil) {
        this.userInfoService = userInfoService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public ResponseEntity<Response> login(@RequestBody UserDTO requestDTO) throws RuntimeException {
        UserDTO userDTO= userInfoService.UserLogin(requestDTO);
        log.info("userDTO : {}", userDTO);
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        if (userDTO !=  null){
            responseBody = userDTO;
            successOrFailMessage = "Logon successfully.";
            //TO CREATE NEW AUTH TOKEN AND SEND IT AS PART OF LOGIN RESPONSE
            jwtUtil.createJWT(userDTO);
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Invalid Credential.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }

}










package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.impl.UserInfoServiceImpl;
import com.nus.iss.tasktracker.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.nullValue;


@WebMvcTest(UserInfoController.class)
class UserInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInfoServiceImpl userInfoService;

    @MockBean
    private JWTUtil jwtUtil;

    @Test
    void testLogin_ValidCredentials() throws  Exception{

        String requestBody= """
                {
                    "username": "User1",
                    "password": "password1"
                }
                """;

        UserDTO validUserDTO =new UserDTO();
        validUserDTO.setUserId(1);
        validUserDTO.setUsername("User1");
        validUserDTO.setUserRole("ROLE_USER");
        validUserDTO.setPassword("password1");


        when(userInfoService.UserLogin(any(UserDTO.class)))
                .thenAnswer( x -> {
                    validUserDTO.setPassword("");
                    return validUserDTO;
                });

        mockMvc.perform(post("/userinfo/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.username").value("User1"))
                .andExpect(jsonPath("$.body.password").value(""))
                .andExpect(jsonPath("$.body.userRole").value("ROLE_USER"))
                .andExpect(jsonPath("$.message").value("Logon successfully."))
                .andExpect(jsonPath("$.error").value(""));
    }


    @Test
    void testLogin_InValidCredentials() throws  Exception{

        String requestBody= """
                {
                    "username": "User1",
                    "password": "password1"
                }
                """;


        when(userInfoService.UserLogin(any(UserDTO.class))). thenReturn(null);
        mockMvc.perform(post("/userinfo/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Invalid Credential."));
    }

}

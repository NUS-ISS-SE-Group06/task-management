package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.impl.UserRegistrationServiceImpl;
import com.nus.iss.tasktracker.util.JWTUtil;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.nullValue;


@WebMvcTest(UserRegistrationController.class)
class UserRegistrationControllerTest {
  /*  @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRegistrationServiceImpl userRegistrationService;

    @MockBean
    private UserInfoRepository userInfoRepository;

    @MockBean
    private JWTUtil jwtUtil;

    private JWTUtil jwtUtil2;

  @Test
    void testSignUp_Success() throws  Exception{

        String requestBody= """
                {
                       "name":"Firstname1 Lastname1",
                       "email":"User1@test.test",
                       "username" : "user1",
                       "password": "password1",
                       "createdBy": "admin"
                }
                """;

        UserDTO userDTO =new UserDTO();
        userDTO.setUserId(1);
        userDTO.setName("Firstname1 Lastname1");
        userDTO.setEmail("User1@test.test");
        userDTO.setUsername("user1");
        userDTO.setPassword("password1");
        userDTO.setCreatedBy("admin");
        userDTO.setPasswordChangeMandatory("FALSE");


        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    userDTO.setPassword(null);
                    userDTO.setOldPassword(null);
                    userDTO.setNewPassword(null);
                    userDTO.setAuthToken(null);
                    userDTO.setCreatedBy(null);
                    return userDTO;
                });



        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.userId").value(1))
                .andExpect(jsonPath("$.body.groupId").value(nullValue()))
                .andExpect(jsonPath("$.body.email").value("User1@test.test"))
                .andExpect(jsonPath("$.body.name").value("Firstname1 Lastname1"))
                .andExpect(jsonPath("$.body.username").value("user1"))
                .andExpect(jsonPath("$.body.password").value(nullValue()))
                .andExpect(jsonPath("$.body.userRole").value(nullValue()))
                .andExpect(jsonPath("$.body.passwordChangeMandatory").value("FALSE"))
                .andExpect(jsonPath("$.body.oldPassword").value(nullValue()))
                .andExpect(jsonPath("$.body.newPassword").value(nullValue()))
                .andExpect(jsonPath("$.body.authToken").value(nullValue()))
                .andExpect(jsonPath("$.body.groupName").value(nullValue()))
                .andExpect(jsonPath("$.body.createdBy").value(nullValue()))

                .andExpect(jsonPath("$.message").value("SignUp successfully."))
                .andExpect(jsonPath("$.error").value(""));

    }


    @Test
    void testSignUp_Failed() throws  Exception{

        String requestBody= """
                {
                       "name":"Firstname1 Lastname1",
                       "email":"User1@test.test",
                       "username" : "user1",
                       "password": "password1",
                       "createdBy": "admin"
                }
                """;

        UserDTO userDTO =null;

        //Username not available!"
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Username not available!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Username not available!"));


        //Name - Please input value!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Name - Please input value!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Name - Please input value!"));


        //Email - Please input value!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Email - Please input value!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Email - Please input value!"));

        //Password - Please input value!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Password - Please input value!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Password - Please input value!"));



        //Password must meet the min. length of 8!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Password must meet the min. length of 8!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Password must meet the min. length of 8!"));


        //Password must contain alphanumeric characters!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Password must contain alphanumeric characters!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Password must contain alphanumeric characters!"));


    }


  @Test
  void testGetAllUsers_SUCCESS_RESPONSE() throws  Exception{
    UserDTO userDTO1 = new UserDTO();
    userDTO1.setUserId(1);
    userDTO1.setEmail("User1@test.test");
    userDTO1.setName("Firstname1 Lastname1");
    userDTO1.setUsername("user1");
    userDTO1.setUserRole("ROLE_ADMIN");
    userDTO1.setGroupId(1);

    UserDTO userDTO2 = new UserDTO();
    userDTO2.setUserId(2);
    userDTO2.setEmail("User2@test.test");
    userDTO2.setName("Firstname2 Lastname2");
    userDTO2.setUsername("user2");
    userDTO2.setUserRole("ROLE_ADMIN");
    userDTO2.setGroupId(1);

    UserDTO userDTO3 = new UserDTO();
    userDTO3.setUserId(3);
    userDTO3.setEmail("User3@test.test");
    userDTO3.setName("Firstname3 Lastname3");
    userDTO3.setUsername("user3");
    userDTO3.setUserRole("ROLE_ADMIN");
    userDTO3.setGroupId(1);

    List<UserDTO> userDTOList = new ArrayList<UserDTO>();
    userDTOList.add(userDTO1);
    userDTOList.add(userDTO2);
    userDTOList.add(userDTO3);

    when(userRegistrationService.getAllUsersInAGroup()).thenReturn(userDTOList);
    mockMvc.perform(get("/userlist")
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].userId").value(1));
  }




    @Test
    void testGetUserById_Success() throws  Exception{
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setUserId(1);
        userDTO1.setEmail("User1@test.test");
        userDTO1.setName("Firstname1 Lastname1");
        userDTO1.setUsername("user1");
        userDTO1.setUserRole("ROLE_ADMIN");
        userDTO1.setGroupId(1);


        when(userRegistrationService.getUserById(eq(1))).thenReturn(userDTO1);
        mockMvc.perform(get("/user?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.username").value("user1"));
    }




    @Test
    void testChangePassword_Success() throws  Exception{

        String requestBody= """
                {
                       "name":"Firstname1 Lastname1",
                       "email":"User1@test.test",
                       "username" : "user1",
                       "password": "password1",
                       "createdBy": "admin"
                }
                """;


        doNothing().when(userRegistrationService).changePassword(any());

        mockMvc.perform(post("/change-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Password updated successfully."));
    }




    @Test
    void testUpdateUserInfo_Success() throws  Exception{

        String requestBody= """
                {
                       "userId":1,
                       "name":"Ben Washington",
                       "modifiedBy": "admin"
                }
                """;
        UserDTO requestDTO=new UserDTO();
        requestDTO.setUserId(1);
        requestDTO.setName("Ben Washington");
        requestDTO.setModifiedBy("admin");


        UserDTO expectedUserDTO =new UserDTO();
        expectedUserDTO.setUserId(1);
        expectedUserDTO.setName("Ben Washington");
        expectedUserDTO.setModifiedBy("admin");


        when(userRegistrationService.updateUserInfo(any(UserDTO.class))).thenReturn(expectedUserDTO);


        mockMvc.perform(post("/userupdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.userId").value(1))
                .andExpect(jsonPath("$.body.name").value("Ben Washington"))
                .andExpect(jsonPath("$.body.modifiedBy").value("admin"))
                .andExpect(jsonPath("$.message").value("User Details Updated successfully."))
                .andExpect(jsonPath("$.error").value(""));

    }




    @Test
    void testUpdateUserInfo_Exception() throws  Exception{

        String requestBody= """
                {
                       "userId":1,
                       "name":"Ben Washington",
                       "modifiedBy": "admin"
                }
                """;
        UserDTO requestDTO=new UserDTO();
        requestDTO.setUserId(1);
        requestDTO.setName("Ben Washington");
        requestDTO.setModifiedBy("admin");

        RuntimeException exception =new RuntimeException("Test Exception");

        when(userRegistrationService.updateUserInfo(any(UserDTO.class))).thenThrow(exception);


        mockMvc.perform(post("/userupdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Test Exception"));//

    }
    */

}

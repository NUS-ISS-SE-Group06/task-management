package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.service.impl.GroupInfoServiceImpl;
import com.nus.iss.tasktracker.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupInfoController.class)
public class GroupInfoControllerTest {
   /* @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupInfoServiceImpl groupInfoService;

    @MockBean
    private JWTUtil jwtUtil;

    @Test
    void testCreateGroup_Success() throws Exception {
        *//*
         Sample JSON input:

        {
            "groupName": "Group TestABC",
            "groupDescription": "Description for Group TestABC",
            "createdBy": "SiBa",
            "deleteFlag": "FALSE"
        }

        *//*


        String requestBody= """
                {
                       "groupName":"Test-Group-Name",
                       "groupDescription":"Test-Group-Description",
                       "createdBy" : "user1",
                       "deleteFlag": "FALSE"
                }
                """;

        GroupDTO groupDTORequest=new GroupDTO();

        when(groupInfoService.createGroup(any(GroupDTO.class)))
                .thenAnswer( x -> {
                    groupDTORequest.setGroupId(1);
                    groupDTORequest.setGroupName("Group TestABC");
                    groupDTORequest.setGroupDescription("Description for Group TestABC");
                    groupDTORequest.setCreatedBy("Admin");
                    groupDTORequest.setDeleteFlag("FALSE");
                    return groupDTORequest;
                });

        *//*
            Sample JSON Output:
         {
            "timestamp": "2024-04-20T10:55:10.03867",
            "status": 200,
            "error": "",
            "message": "Group Created successfully.",
            "body": {
                "groupId": 4,
                "groupName": "Group TestABC",
                "groupDescription": "Description for Group TestABC",
                "createdBy": "SiBa",
                "modifiedBy": null,
                "deleteFlag": "FALSE"
            }
        }

         *//*

        mockMvc.perform(post("/group-info/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.groupId").value(1))
                .andExpect(jsonPath("$.body.groupName").value("Group TestABC"))
                .andExpect(jsonPath("$.body.groupDescription").value("Description for Group TestABC"))
                .andExpect(jsonPath("$.body.createdBy").value("Admin"))
                .andExpect(jsonPath("$.body.deleteFlag").value("FALSE"))
                .andExpect(jsonPath("$.message").value("Group Created successfully."))
                .andExpect(jsonPath("$.error").value(""));

    }



    @Test
    void testCreateGroup_Failed() throws Exception {
        *//*
         Sample JSON input:

        {
            "groupName": "Group TestABC",
            "groupDescription": "Description for Group TestABC",
            "createdBy": "user1",
            "deleteFlag": "FALSE"
        }

        *//*

        String requestBody= """
                {
                       "groupName":"Test-Group-Name",
                       "groupDescription":"Test-Group-Description",
                       "createdBy" : "user1",
                       "deleteFlag": "FALSE"
                }
                """;

        when(groupInfoService.createGroup(any(GroupDTO.class))).thenReturn(null);

        *//*
            Sample JSON Output:
         {
                "timestamp": "2024-04-20T13:28:59.318777",
                "status": 200,
                "error": "Group Creation Failed.",
                "message": "",
                "body": null
          }

         *//*

        mockMvc.perform(post("/group-info/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Group Creation Failed."));
    }


    @Test
    void testGetGroupById_Success() throws Exception {

        GroupDTO groupDTORequest=new GroupDTO();

        when(groupInfoService.getGroupById(1))
                .thenAnswer( x -> {
                    groupDTORequest.setGroupId(1);
                    groupDTORequest.setGroupName("Group TestABC");
                    groupDTORequest.setGroupDescription("Description for Group TestABC");
                    groupDTORequest.setCreatedBy("Admin");
                    groupDTORequest.setDeleteFlag("FALSE");
                    return groupDTORequest;
                });

        *//*
            Sample JSON Output:

            {
                "timestamp": "2024-04-20T13:03:33.242123",
                "status": 200,
                "error": "",
                "message": "Group Info Retrieved successfully.",
                "body": {
                    "groupId": 1,
                    "groupName": "Group TestABC",
                    "groupDescription": "Description for Group TestABC",
                    "createdBy": "Admin",
                    "modifiedBy": null,
                    "deleteFlag": "FALSE"
                }

         *//*

        mockMvc.perform(get("/group-info/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.groupId").value(1))
                .andExpect(jsonPath("$.body.groupName").value("Group TestABC"))
                .andExpect(jsonPath("$.body.groupDescription").value("Description for Group TestABC"))
                .andExpect(jsonPath("$.body.createdBy").value("Admin"))
                .andExpect(jsonPath("$.body.deleteFlag").value("FALSE"))
                .andExpect(jsonPath("$.message").value("Group Info Retrieved successfully."))
                .andExpect(jsonPath("$.error").value(""));

    }


    @Test
    void testGetGroupById_Failed() throws Exception {

        when(groupInfoService.getGroupById(1)).thenReturn(null);

        *//*
            Sample JSON Output:

            {
                "timestamp": "2024-04-20T13:28:59.318777",
                "status": 200,
                "error": "Group Info Retrieval Failed.",
                "message": "",
                "body": null
            }

         *//*

        mockMvc.perform(get("/group-info/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Group Info Retrieval Failed."));

    }*/

}



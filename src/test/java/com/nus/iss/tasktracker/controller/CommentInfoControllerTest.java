package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.TaskCommentDTO;
import com.nus.iss.tasktracker.service.impl.CommentInfoServiceImpl;
import com.nus.iss.tasktracker.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;


@WebMvcTest(CommentInfoController.class)
public class CommentInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentInfoServiceImpl commentInfoService;

    @MockBean
    private JWTUtil jwtUtil;

    @Test
    void testCreateComment_Success() throws Exception {
         /*
         Sample JSON input:

        {
            "taskId":1,
            "taskComment": "Test Comment 1",
            "createdBy": "Admin"
        }

        */

        String requestBody= """
                {
                       "taskId":"1",
                       "taskComment":"Task Comment 1",
                       "createdBy" : "user1"
                }
                """;

        TaskCommentDTO taskCommentDTO=new TaskCommentDTO();

        when(commentInfoService.saveComment(any(TaskCommentDTO.class)))
                .thenAnswer( x -> {
                    taskCommentDTO.setTaskId(1);
                    taskCommentDTO.setTaskComment("Task Comment 1");
                    taskCommentDTO.setCreatedBy("user1");
                    return taskCommentDTO;
                });

        /*
            Sample JSON Output:
             {
                "timestamp": "2024-04-20T17:04:10.815992",
                "status": 200,
                "error": "",
                "message": "Comment Created successfully.",
                "body": {
                    "taskCommentId": 9,
                    "taskId": 1,
                    "taskComment": "Test Comment1",
                    "createdBy": "user1",
                    "createdDate": null
                }
            }

         */

        mockMvc.perform(post("/comment-info/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.taskId").value(1))
                .andExpect(jsonPath("$.body.taskComment").value("Task Comment 1"))
                .andExpect(jsonPath("$.body.createdBy").value("user1"))
                .andExpect(jsonPath("$.message").value("Comment Created successfully."))
                .andExpect(jsonPath("$.error").value(""));
    }


    @Test
    void testCreateComment_Failed() throws Exception {
         /*
         Sample JSON input:

        {
            "taskId":-1,
            "taskComment": "Test Comment1",
            "createdBy": "Admin"
        }


        */

        String requestBody= """
                {
                       "taskId":"-1",
                       "taskComment":"Task Comment 1",
                       "createdBy" : "user1"
                }
                """;

        when(commentInfoService.saveComment(any(TaskCommentDTO.class))).thenReturn(null);

        /*
            Sample JSON Output:

           {
            "timestamp": "2024-04-20T17:05:33.600529",
            "status": 200,
            "error": "Comment Creation Failed.",
            "message": "",
            "body": null
            }

         */

        mockMvc.perform(post("/comment-info/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Comment Creation Failed."));
    }



    @Test
    void testGetAllCommentsForTask_Success() throws Exception {

        List<TaskCommentDTO> taskCommentList = new ArrayList<>();
        TaskCommentDTO taskCommentDTO1=new TaskCommentDTO();
        taskCommentDTO1.setTaskId(1);
        taskCommentDTO1.setTaskComment("Task Comment 1");
        taskCommentDTO1.setCreatedBy("user1");
        taskCommentList.add(taskCommentDTO1);

        TaskCommentDTO taskCommentDTO2=new TaskCommentDTO();
        taskCommentDTO2.setTaskId(2);
        taskCommentDTO2.setTaskComment("Task Comment 2");
        taskCommentDTO2.setCreatedBy("user1");
        taskCommentList.add(taskCommentDTO2);


        when(commentInfoService.getAllCommentsForTask(1)).thenReturn(taskCommentList);

        /*
            Sample JSON Output:

                    [
                        {
                            "taskCommentId": 1,
                            "taskId": 1,
                            "taskComment": "test",
                            "createdBy": "user1",
                            "createdDate": null
                        },
                    ]

         */


        mockMvc.perform(get("/comment-info/comment-list/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }




    @Test
    void testGetAllCommentsForTask_Failed() throws Exception {

        List<TaskCommentDTO> taskCommentList = new ArrayList<>();
        when(commentInfoService.getAllCommentsForTask(1)).thenReturn(taskCommentList);

        /*
            Sample JSON Output:

                    [
                    ]

         */


        mockMvc.perform(get("/comment-info/comment-list/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    void testGetCommentById_Success() throws Exception {

        TaskCommentDTO taskCommentDTO=new TaskCommentDTO();
        taskCommentDTO.setTaskId(1);
        taskCommentDTO.setTaskCommentId(1);
        taskCommentDTO.setTaskComment("Task Comment 1");
        taskCommentDTO.setCreatedBy("user1");



        when(commentInfoService.getCommentById(1)).thenReturn(taskCommentDTO);

        /*
            Sample JSON Output:

             {
                "timestamp": "2024-04-20T21:48:24.288255",
                "status": 200,
                "error": "",
                "message": "Comment Info Retrieved successfully.",
                "body": {
                    "taskCommentId": 1,
                    "taskId": 1,
                    "taskComment": "test",
                    "createdBy": "user1"
                }
            }

         */


        mockMvc.perform(get("/comment-info/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.taskCommentId").value(1))
                .andExpect(jsonPath("$.body.taskId").value(1))
                .andExpect(jsonPath("$.body.taskComment").value("Task Comment 1"))
                .andExpect(jsonPath("$.body.createdBy").value("user1"))
                .andExpect(jsonPath("$.message").value("Comment Info Retrieved successfully."))
                .andExpect(jsonPath("$.error").value(""));
    }



    @Test
    void testGetCommentById_Failed() throws Exception {

        when(commentInfoService.getCommentById(-1)).thenReturn(null);

        /*
            Sample JSON Output:

            {
                "timestamp": "2024-04-20T21:52:19.641061",
                "status": 200,
                "error": "Comment Info Retrieval Failed.",
                "message": "",
                "body": null
            }


         */


        mockMvc.perform(get("/comment-info/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Comment Info Retrieval Failed."));
    }



}


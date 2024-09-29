package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.impl.TaskInfoServiceImpl;
//import com.nus.iss.tasktracker.service.impl.UserInfoServiceImpl;//comment TTP2-36
import com.nus.iss.tasktracker.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TaskInfoController.class)
public class TaskInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskInfoServiceImpl taskInfoService;

    //comment TTP2-34
    /*
    @MockBean
    private UserInfoServiceImpl userInfoService;
    */

    @MockBean
    private JWTUtil jwtUtil;

/*
    @Test
    void testCreateTask_Success() throws  Exception{
         *//*
         Sample JSON input:

        {
            "taskName":"test again11",
            "taskDescription":"test again11",
            "taskAssignee":"1",
            "taskDueDate":"2024-04-20 22:28:00",
            "taskRewardPoint":0,
            "taskPriority":"2",
            "taskCategoryId":"2",
            "taskStatus":"Pending"
        }

        *//*


        String requestBody= """
                {
                   "taskName":"Task-Name1",
                   "taskDescription":"Task-Description1",
                   "taskAssignee":"1",
                   "taskDueDate":"2024-04-20 22:28:00",
                   "taskRewardPoint":100,
                   "taskPriority":"2",
                   "taskCategoryId":"2",
                   "taskStatus":"Pending"
                }
                """;


        TaskInfoDTO taskInfoDTO=new TaskInfoDTO();

        taskInfoDTO.setTaskId(1);
        taskInfoDTO.setTaskName("Task-Name1");
        taskInfoDTO.setTaskDescription("Task-Description1");
        taskInfoDTO.setTaskAssignee(1);
        taskInfoDTO.setTaskDueDate(Timestamp.valueOf("2024-04-20 22:28:00"));
        taskInfoDTO.setTaskRewardPoint(100);
        taskInfoDTO.setTaskPriority("2");
        taskInfoDTO.setTaskCategoryId(2);
        taskInfoDTO.setTaskStatus("Pending");
        taskInfoDTO.setCreatedBy("user1");
        taskInfoDTO.setCreatedDate(Timestamp.valueOf("2024-04-20 22:28:00"));
        taskInfoDTO.setDeleteFlag("FALSE");

        when(taskInfoService.createTask(any(TaskInfoDTO.class))).thenReturn(taskInfoDTO);

        *//*
            Sample JSON Output:
            {
                "timestamp": "2024-04-20T22:29:05.746851",
                "status": 200,
                "error": "",
                "message": "Task Created Successfully.",
                "body": {
                    "taskId": 12,
                    "taskName": "test again11",
                    "taskDescription": "test again11",
                    "taskPriority": "2",
                    "taskCategoryId": 2,
                    "taskDueDate": "2024-04-20 22:28:00",
                    "taskAssignee": 1,
                    "taskRewardPoint": 0,
                    "taskStatus": "Pending",
                    "createdBy": "ADMIN",
                    "createdDate": "2024-04-20 14:29:05",
                    "modifiedBy": "ADMIN",
                    "modifiedDate": "2024-04-20 14:29:05",
                    "deleteFlag": "FALSE"
                }
            }

         *//*


        mockMvc.perform(post("/taskinfo/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.taskId").value(1))
                .andExpect(jsonPath("$.body.taskName").value("Task-Name1"))
                .andExpect(jsonPath("$.body.taskDescription").value("Task-Description1"))
                .andExpect(jsonPath("$.body.taskPriority").value("2"))
                .andExpect(jsonPath("$.body.taskCategoryId").value(2))
                .andExpect(jsonPath("$.body.taskAssignee").value(1))
                .andExpect(jsonPath("$.body.taskRewardPoint").value(100))
                .andExpect(jsonPath("$.body.taskStatus").value("Pending"))
                .andExpect(jsonPath("$.body.createdBy").value("user1"))
                .andExpect(jsonPath("$.message").value("Task Created Successfully."))
                .andExpect(jsonPath("$.error").value(""));


    }*/

/*

    @Test
    void testCreateTask_Failed() throws  Exception{
         *//*
         Sample JSON input:

        {
            "taskName":"test again11",
            "taskDescription":"test again11",
            "taskAssignee":"1",
            "taskDueDate":"2024-04-20 22:28:00",
            "taskRewardPoint":0,
            "taskPriority":"2",
            "taskCategoryId":"2",
            "taskStatus":"Pending"
        }

        *//*


        String requestBody= """
                {
                   "taskName":"Task-Name1",
                   "taskDescription":"Task-Description1",
                   "taskAssignee":"1",
                   "taskDueDate":"2024-04-20 22:28:00",
                   "taskRewardPoint":100,
                   "taskPriority":"2",
                   "taskCategoryId":"2",
                   "taskStatus":"Pending"
                }
                """;

        TaskInfoDTO taskInfoDTO=new TaskInfoDTO();
        taskInfoDTO.setTaskId(1);
        taskInfoDTO.setTaskName("Task-Name1");
        taskInfoDTO.setTaskDescription("Task-Description1");
        taskInfoDTO.setTaskAssignee(1);
        taskInfoDTO.setTaskDueDate(Timestamp.valueOf("2024-04-20 22:28:00"));
        taskInfoDTO.setTaskRewardPoint(100);
        taskInfoDTO.setTaskPriority("2");
        taskInfoDTO.setTaskCategoryId(2);
        taskInfoDTO.setTaskStatus("Pending");
        taskInfoDTO.setCreatedBy("user1");
        taskInfoDTO.setCreatedDate(Timestamp.valueOf("2024-04-20 22:28:00"));
        taskInfoDTO.setDeleteFlag("FALSE");

        when(taskInfoService.createTask(any(TaskInfoDTO.class))).thenReturn(null);

        *//*
            Sample JSON Output:
            {
                "timestamp": "2024-04-20T22:29:05.746851",
                "status": 200,
                "error": "",
                "message": "",
                "body": null
            }

         *//*


        mockMvc.perform(post("/taskinfo/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value(""));


    }*/




    @Test
    void testDeleteTask_Success() throws  Exception{

        TaskInfoDTO taskInfoDTO=new TaskInfoDTO();
        taskInfoDTO.setTaskId(1);

        when(taskInfoService.deleteTask(1)).thenReturn(taskInfoDTO);

        /*
            Sample JSON Output:
            {
                "timestamp": "2024-04-20T22:29:05.746851",
                "status": 200,
                "error": "",
                "message": "Task Deleted Successfully.",
                "body": {
                    "taskId": 12,
                    "taskName": "test again11",
                    "taskDescription": "test again11",
                    "taskPriority": "2",
                    "taskCategoryId": 2,
                    "taskDueDate": "2024-04-20 22:28:00",
                    "taskAssignee": 1,
                    "taskRewardPoint": 0,
                    "taskStatus": "Pending",
                    "createdBy": "ADMIN",
                    "createdDate": "2024-04-20 14:29:05",
                    "modifiedBy": "ADMIN",
                    "modifiedDate": "2024-04-20 14:29:05",
                    "deleteFlag": "FALSE"
                }
            }

         */


        mockMvc.perform(delete("/taskinfo/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.taskId").value(1))
                .andExpect(jsonPath("$.message").value("Task Record Deleted Successfully."))
                .andExpect(jsonPath("$.error").value(""));


    }



    @Test
    void testDeleteTask_Failed() throws  Exception{

        when(taskInfoService.deleteTask(1)).thenReturn(null);

        /*
            Sample JSON Output:
            {
                "timestamp": "2024-04-20T22:29:05.746851",
                "status": 200,
                "error": "",
                "message": "",
                "body": null
            }

         */


        mockMvc.perform(delete("/taskinfo/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value(""));


    }





    @Test
    void testGetAllTasks_Success() throws  Exception{


        List<TaskInfoDTO> taskInfoDTOList = new ArrayList<>();
        TaskInfoDTO taskInfoDTO1=new TaskInfoDTO();
        taskInfoDTO1.setTaskId(1);
        taskInfoDTOList.add(taskInfoDTO1);
        TaskInfoDTO taskInfoDTO2=new TaskInfoDTO();
        taskInfoDTO2.setTaskId(2);
        taskInfoDTOList.add(taskInfoDTO2);

        when(taskInfoService.getAllActiveTasks()).thenReturn(taskInfoDTOList);

        mockMvc.perform(get("/taskinfo/tasklist")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));


    }




    @Test
    void testGetAllTasks_Failed() throws  Exception{

        when(taskInfoService.getAllActiveTasks()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/taskinfo/tasklist")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));


    }





    @Test
    void testGetAllTasksAssignedDue_Success() throws  Exception{


        List<TaskInfoDTO> taskInfoDTOList = new ArrayList<>();
        TaskInfoDTO taskInfoDTO1=new TaskInfoDTO();
        taskInfoDTO1.setTaskId(1);
        taskInfoDTOList.add(taskInfoDTO1);
        TaskInfoDTO taskInfoDTO2=new TaskInfoDTO();
        taskInfoDTO2.setTaskId(2);
        taskInfoDTOList.add(taskInfoDTO2);

        when(taskInfoService.getAllActiveTasksAssignedDue()).thenReturn(taskInfoDTOList);

        mockMvc.perform(get("/taskinfo/tasklistdue")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));


    }



    @Test
    void testGetAllTasksAssignedDue_Failed() throws  Exception{

        when(taskInfoService.getAllActiveTasksAssignedDue()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/taskinfo/tasklistdue")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));


    }









/*

    @Test
    void testUpdateTask_Success() throws  Exception{

        String requestBody= """
                {
                   "taskName":"Task-Name1",
                   "taskDescription":"Task-Description1",
                   "taskAssignee":"1",
                   "taskDueDate":"2024-04-20 22:28:00",
                   "taskRewardPoint":100,
                   "taskPriority":"2",
                   "taskCategoryId":"2",
                   "taskStatus":"Pending"
                }
                """;

        TaskInfoDTO updateTaskInfoDTO=new TaskInfoDTO();
        updateTaskInfoDTO.setTaskId(1);
        updateTaskInfoDTO.setTaskName("Task-Name1");
        updateTaskInfoDTO.setTaskDescription("Task-Description1");
        updateTaskInfoDTO.setTaskAssignee(1);
        updateTaskInfoDTO.setTaskDueDate(Timestamp.valueOf("2024-04-20 22:28:00"));
        updateTaskInfoDTO.setTaskRewardPoint(100);
        updateTaskInfoDTO.setTaskPriority("2");
        updateTaskInfoDTO.setTaskCategoryId(2);
        updateTaskInfoDTO.setTaskStatus("Pending");
        updateTaskInfoDTO.setCreatedBy("user1");
        updateTaskInfoDTO.setCreatedDate(Timestamp.valueOf("2024-04-20 22:28:00"));
        updateTaskInfoDTO.setDeleteFlag("FALSE");


        when(taskInfoService.updateTask(eq(1),any(TaskInfoDTO.class))).thenReturn(updateTaskInfoDTO);

        mockMvc.perform(put("/taskinfo/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.taskName").value("Task-Name1"))
                .andExpect(jsonPath("$.body.taskDescription").value("Task-Description1"))
                .andExpect(jsonPath("$.body.taskPriority").value("2"))
                .andExpect(jsonPath("$.body.taskCategoryId").value(2))
                .andExpect(jsonPath("$.body.taskAssignee").value(1))
                .andExpect(jsonPath("$.body.taskRewardPoint").value(100))
                .andExpect(jsonPath("$.body.taskStatus").value("Pending"))
                .andExpect(jsonPath("$.body.createdBy").value("user1"))
                .andExpect(jsonPath("$.message").value("Task Updated Successfully."))
                .andExpect(jsonPath("$.error").value(""));



    }*/



/*

    @Test
    void testUpdateTask_Failed() throws  Exception{

        String requestBody= """
                {
                   "taskName":"Task-Name1",
                   "taskDescription":"Task-Description1",
                   "taskAssignee":"1",
                   "taskDueDate":"2024-04-20 22:28:00",
                   "taskRewardPoint":100,
                   "taskPriority":"2",
                   "taskCategoryId":"2",
                   "taskStatus":"Pending"
                }
                """;



        when(taskInfoService.updateTask(eq(1),any(TaskInfoDTO.class))).thenReturn(null);

        mockMvc.perform(put("/taskinfo/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value(""));



    }
*/




}






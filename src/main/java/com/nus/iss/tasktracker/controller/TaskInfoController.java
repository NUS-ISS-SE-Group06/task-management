package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/taskinfo")
//@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL, TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
public class TaskInfoController {
    private final TaskInfoService taskInfoService;

    public TaskInfoController(TaskInfoService taskInfoService) {

        this.taskInfoService = taskInfoService;
    }

    @PostMapping("/create")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL,  TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public ResponseEntity<Response> createTask(@RequestBody TaskInfoDTO requestDTO) throws RuntimeException {

        TaskInfoDTO taskInfoDTO = null;
        Object responseBody = null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage = "";
        try {
            taskInfoDTO = taskInfoService.createTask(requestDTO);
        } catch (Exception e) {
            successOrFailMessage = e.getMessage();
        }

        if (taskInfoDTO != null) {
            responseBody = taskInfoDTO;
            successOrFailMessage = String.format(TaskTrackerConstant.RECORD_CREATED_SUCCESSFULLY, TaskTrackerConstant.TASK_TRACKER_MODULE_TASK);
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }

    }

    @DeleteMapping("/delete/{id}")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL,  TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public ResponseEntity<Response> deleteTask(@PathVariable int id) {

        TaskInfoDTO taskInfoDTO = null;
        Object responseBody = null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage = "";
        try {
            taskInfoDTO = taskInfoService.deleteTask(id);
        } catch (Exception e) {
            successOrFailMessage = e.getMessage();
        }
        if (taskInfoDTO != null) {
            responseBody = taskInfoDTO;
            successOrFailMessage = String.format(TaskTrackerConstant.RECORD_DELETED_SUCCESSFULLY,  TaskTrackerConstant.TASK_TRACKER_MODULE_TASK);
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }

    @GetMapping("tasklist")
   //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL,  TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public List<TaskInfoDTO> getAllTasks() {

        return taskInfoService.getAllActiveTasks();
    }

    @PutMapping("/update/{taskId}")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL,  TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public ResponseEntity<Response> updateTask(@PathVariable("taskId") int taskId, @RequestBody TaskInfoDTO requestDTO) {

        TaskInfoDTO taskInfoDTO = null;
        Object responseBody = null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage = "";
        try {
            taskInfoDTO = taskInfoService.updateTask(taskId, requestDTO);
        } catch (Exception e) {
            successOrFailMessage = e.getMessage();
        }

        if (taskInfoDTO != null) {
            responseBody = taskInfoDTO;
            successOrFailMessage = String.format(TaskTrackerConstant.RECORD_UPDATED_SUCCESSFULLY,  TaskTrackerConstant.TASK_TRACKER_MODULE_TASK);
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }

    @GetMapping("tasklistdue")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL,  TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public List<TaskInfoDTO> getAllTasksAssignedDue() {
        return taskInfoService.getAllActiveTasksAssignedDue();
    }


    @GetMapping("/totalreward/{userId}")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL, TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public Integer getLeaderboard ( @PathVariable("userId") int userId){
        return taskInfoService.findTaskRewardPointsByGroupId(userId);
    }

}


package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.TaskCommentDTO;
import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.service.CommentInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
//import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comment-info")
@Slf4j
public class CommentInfoController {

    private final CommentInfoService commentInfoService;

    @Autowired
    public CommentInfoController(CommentInfoService commentInfoService) {
        this.commentInfoService = commentInfoService;
    }

    @GetMapping("/{id}")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL, TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public ResponseEntity<Response> getCommentById(@PathVariable int id) throws RuntimeException{
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        TaskCommentDTO taskCommentDTO = commentInfoService.getCommentById(id);

        if (taskCommentDTO !=  null){
            responseBody = taskCommentDTO;
            successOrFailMessage = "Comment Info Retrieved successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Comment Info Retrieval Failed.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }

    }


    @PostMapping("/create")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL, TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public ResponseEntity<Response> createComment(@RequestBody TaskCommentDTO requestDTO) throws RuntimeException {
        TaskCommentDTO taskCommentDTO = commentInfoService.saveComment(requestDTO);
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        if (taskCommentDTO !=  null){
            responseBody = taskCommentDTO;
            successOrFailMessage = "Comment Created successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Comment Creation Failed.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }

    @GetMapping("/comment-list/{id}")
    //@CrossOrigin(origins = {TaskTrackerConstant.CROSS_ORIGIN_URL, TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL})
    public List<TaskCommentDTO> getAllCommentsForTask(@PathVariable int id){
        return commentInfoService.getAllCommentsForTask(id);
    }
}

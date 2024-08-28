package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.TaskCommentDTO;
import java.util.List;

public interface CommentInfoService {

    public TaskCommentDTO getCommentById(int commentId);

    public TaskCommentDTO saveComment(TaskCommentDTO taskCommentDTO);

    public List<TaskCommentDTO> getAllCommentsForTask(int taskId);
}

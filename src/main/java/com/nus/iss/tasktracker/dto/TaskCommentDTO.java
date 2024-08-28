package com.nus.iss.tasktracker.dto;

import lombok.Data;

@Data
public class TaskCommentDTO {

    private Integer taskCommentId;

    private Integer taskId;

    private String taskComment;

    private String createdBy;

    private String createdDate;
}

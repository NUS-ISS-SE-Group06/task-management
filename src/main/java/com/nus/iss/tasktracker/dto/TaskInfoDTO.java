package com.nus.iss.tasktracker.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
public class TaskInfoDTO {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private int taskCategoryId;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp taskDueDate;
    private int taskAssignee;
    private int taskRewardPoint;
    private String taskStatus;


    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdDate;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp modifiedDate;
    private String deleteFlag;

    /*

    // Add a constructor matching the fields
    public TaskInfoDTO(int taskId, String taskName, String taskDescription, String taskPriority, int taskCategoryId, Timestamp taskDueDate, int taskAssignee, int taskRewardPoint, String taskStatus, String createdBy, Timestamp createdDate, String modifiedBy, Timestamp modifiedDate, String deleteFlag) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.taskCategoryId = taskCategoryId;
        this.taskDueDate = taskDueDate;
        this.taskAssignee = taskAssignee;
        this.taskRewardPoint = taskRewardPoint;
        this.taskStatus = taskStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.deleteFlag = deleteFlag;
    } */


}

package com.nus.iss.tasktracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "task_info")
public class TaskInfo {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer taskId;

    @Getter
    @Setter
    private String taskName;

    @Getter
    @Setter
    private String taskDescription;

    @Getter
    @Setter
    private String taskPriority;

    @Getter
    @Setter
    private Integer taskCategoryId;

    @Getter
    @Setter
    private  Timestamp taskDueDate;

    @Getter
    @Setter
    private  Integer taskAssignee;

    @Getter
    @Setter
    private  Integer taskRewardPoint;

    @Getter
    @Setter
    private  String taskStatus;

    @Getter
    @Setter
    private  String createdBy;

    @Getter
    @Setter
    private Timestamp createdDate;

    @Getter
    @Setter
    private  String modifiedBy;

    @Getter
    @Setter
    private  Timestamp modifiedDate;

    @Getter
    @Setter
    private String deleteFlag;

    @Getter
    @Setter
    @Transient
    private String taskCategoryName;

    @Getter
    @Setter
    @Transient
    private String taskAssigneeName;


    @Override
    public String toString() {
        return "TaskInfo{" +
                "task_id=" + ((taskId  != null ) ? taskId : "") +
                ", task_name=" + ((taskName != null) ? taskName : "null") +
                ", task_description=" + ((taskDescription != null) ? taskDescription : "null") +
                ", task_priority=" + ((taskPriority != null) ? taskPriority : "null") +
                ", task_category_id=" + ((taskCategoryId != null) ? taskCategoryId : "") +
                ", task_due_date=" + ((getTaskDueDate() != null) ? getTaskDueDate() : "null") +
                ", task_assignee=" + ((taskAssignee != null) ? taskAssignee : "null") +
                ", task_reward_point=" + ((taskRewardPoint != null) ? taskRewardPoint : "null") +
                ", task_status=" + ((taskStatus != null) ? taskStatus : "null") +
                ", created_by=" + ((createdBy != null) ? createdBy : "null")  +
                ", created_date=" + ((createdDate != null) ? createdDate : "null")  +
                ", modified_by=" + ((modifiedBy != null) ? modifiedBy : "null")  +
                ", modified_date=" + ((modifiedDate != null) ? modifiedDate : "null")  +
                ", delete_flag=" + ((deleteFlag != null) ? deleteFlag : "null")  +

                //Transient Field - ForeignKey Description
                ", taskCategoryName=" + ((taskCategoryName != null) ? taskCategoryName : "null")  +
                ", taskAssigneeName=" + ((taskAssigneeName != null) ? taskAssigneeName : "null")  +


                '}';
    }


}




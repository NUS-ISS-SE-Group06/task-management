package com.nus.iss.tasktracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class TaskComments {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer taskCommentId;

    private Integer taskId;

    private String taskComment;

    private String createdBy;

    private Timestamp createdDate;

    @Override
    public String toString() {
        return "TaskComments{" +
                "taskCommentId=" + ((taskCommentId != null) ? taskCommentId : "") +
                ", taskId=" + ((taskId != null) ? taskId : "null") +
                ", taskComment=" + ((taskComment != null) ? taskComment : "null") +
                ", created_by=" + ((createdBy != null) ? createdBy : "null")  +
                ", created_date=" + ((createdDate != null) ? createdDate : "null")  +
                '}';
    }

}

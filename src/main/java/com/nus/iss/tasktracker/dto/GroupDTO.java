package com.nus.iss.tasktracker.dto;

import lombok.Data;

@Data
public class GroupDTO {

    private Integer groupId;

    private String groupName;

    private String groupDescription;

    private String createdBy;

    private String modifiedBy;

    private String deleteFlag;

}

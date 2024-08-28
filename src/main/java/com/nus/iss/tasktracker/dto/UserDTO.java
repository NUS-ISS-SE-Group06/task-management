package com.nus.iss.tasktracker.dto;


import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;

    private Integer groupId;

    private String email;

    private String name;

    private String username;

    private String password;

    private String userRole;

    private String passwordChangeMandatory;

    private String oldPassword;

    private String newPassword;

    private String authToken;

    private String groupName;

    private String createdBy;

    private String modifiedBy;

    private String deleteFlag;

}

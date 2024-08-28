package com.nus.iss.tasktracker.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
public class UserInfo {

    public UserInfo(){
        //Database - Default Value
        this.deleteFlag="FALSE";
        this.passwordChangeMandatory="TRUE";
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;

    @Getter
    @Setter
    private Integer groupId;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private  String password;

    @Getter
    @Setter
    private  String role;

    @Getter
    @Setter
    private  String passwordChangeMandatory;

    @Getter
    @Setter
    private  String createdBy;

    @Getter
    @Setter
    private  Timestamp createdDate;

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
    private String groupName;



    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + ((userId  != null ) ? userId : "") +
                ", name=" + ((name != null) ? name : "null") +
                ", email=" + ((email != null) ? email : "null") +
                ", username=" + ((username != null) ? username : "null") +
                ", group_id=" + ((groupId != null) ? groupId : "null") +
                ", role=" + ((role != null) ? role : "null") +
                ", password_change_mandatory=" + ((passwordChangeMandatory != null ) ? passwordChangeMandatory : "null") +
                ", created_by=" + ((createdBy != null) ? createdBy : "null")  +
                ", created_date=" + ((createdDate != null) ? createdDate : "null")  +
                ", modified_by=" + ((modifiedBy != null) ? modifiedBy : "null")  +
                ", modified_date=" + ((modifiedDate != null) ? modifiedDate : "null")  +
                ", delete_flag=" + ((deleteFlag != null) ? deleteFlag : "null")  +

                //Transient Field - ForeignKey Description
                ", groupName=" + ((groupName != null) ? groupName : "null")  +

                '}';
    }



}

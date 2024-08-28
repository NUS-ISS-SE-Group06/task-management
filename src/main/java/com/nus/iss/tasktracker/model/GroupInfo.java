package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class GroupInfo {

    public GroupInfo(){
        //Database - Default Value
        this.deleteFlag="FALSE";
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer groupId;

    @Getter
    @Setter
    private String groupName;

    @Getter
    @Setter
    private  String groupDescription;

    @Getter
    @Setter
    private  String createdBy;

    @Getter
    @Setter
    private  String createdDate;

    @Getter
    @Setter
    private  String modifiedBy;

    @Getter
    @Setter
    private  String modifiedDate;

    @Getter
    @Setter
    private String deleteFlag;


    //Transient Field - ForeignKey Description

    @Override
    public String toString() {
        return "GroupInfo{" +
                "group_id=" + ((groupId != null) ? groupId : "") +
                ", group_name=" + ((groupName != null) ? groupName : "null") +
                ", group_description=" + ((groupDescription != null) ? groupDescription : "null") +
                ", created_by=" + ((createdBy != null) ? createdBy : "null")  +
                ", created_date=" + ((createdDate != null) ? createdDate : "null")  +
                ", modified_by=" + ((modifiedBy != null) ? modifiedBy : "null")  +
                ", modified_date=" + ((modifiedDate != null) ? modifiedDate : "null")  +
                ", delete_flag=" + ((deleteFlag != null) ? deleteFlag : "null")  +


                '}';
    }


}

package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.model.TaskInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TaskInfoMapper {

    @Mapping(ignore = true, target = "createdDate")
    @Mapping(ignore = true, target = "modifiedDate")
    @Mapping(ignore = true, target = "deleteFlag")

    TaskInfo taskInfoToEntity(TaskInfoDTO requestDTO);

    @Named("toTimestamp")
    default Timestamp toTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }
    TaskInfoDTO taskInfoToTaskinfoDTO(TaskInfo savedTaskInfoEntity);

    @Mappings({
            @Mapping(source = "taskInfo.taskId", target = "taskId"),
            @Mapping(source = "taskInfo.taskName", target = "taskName"),
            @Mapping(source = "taskInfo.taskDescription", target = "taskDescription"),
            @Mapping(source = "taskInfo.taskPriority", target = "taskPriority"),
            @Mapping(source = "taskInfo.taskCategoryId", target = "taskCategoryId"),
            @Mapping(source = "taskInfo.taskDueDate", target = "taskDueDate"),
            @Mapping(source = "taskInfo.taskAssignee", target = "taskAssignee"),
            @Mapping(source = "taskInfo.taskRewardPoint", target = "taskRewardPoint"),
            @Mapping(source = "taskInfo.taskStatus", target = "taskStatus"),
            @Mapping(source = "taskInfo.createdBy", target = "createdBy"),
            @Mapping(source = "taskInfo.createdDate", target = "createdDate"),
            @Mapping(source = "taskInfo.modifiedBy", target = "modifiedBy"),
            @Mapping(source = "taskInfo.modifiedDate", target = "modifiedDate"),
            @Mapping(source = "taskInfo.deleteFlag", target = "deleteFlag")
    })
    TaskInfoDTO taskInfoToTaskDTO(TaskInfo taskInfo);


}

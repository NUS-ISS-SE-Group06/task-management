package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.model.GroupInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO groupInfoToGroupDTO(GroupInfo groupInfo);

    @Mapping(ignore = true, target = "createdDate")
    @Mapping(ignore = true, target = "modifiedDate")
    @Mapping(ignore = true, target = "deleteFlag")
    GroupInfo groupDTOToGroupInfo(GroupDTO groupDTO);

}

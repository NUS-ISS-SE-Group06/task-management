package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.TaskCommentDTO;
import com.nus.iss.tasktracker.model.TaskComments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    TaskCommentDTO commentInfoToCommentDTO(TaskComments taskComments);

    @Mapping(ignore = true, target = "createdDate")
    TaskComments commentDTOToCommentInfo(TaskCommentDTO taskCommentDTO);

}

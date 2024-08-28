package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.TaskCommentDTO;
import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import com.nus.iss.tasktracker.mapper.CommentMapper;
import com.nus.iss.tasktracker.model.TaskComments;
import com.nus.iss.tasktracker.repository.CommentInfoRepository;
import com.nus.iss.tasktracker.service.CommentInfoService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentInfoServiceImpl implements CommentInfoService {

    private  final CommentInfoRepository commentInfoRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentInfoServiceImpl(CommentInfoRepository commentInfoRepository, CommentMapper commentMapper) {
        this.commentInfoRepository = commentInfoRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public TaskCommentDTO getCommentById(int commentId) {
        log.info("commentId: {}",commentId);
        TaskCommentDTO taskCommentDTO = null;
        if(commentId>0){
            Optional<TaskComments> commentInfo = commentInfoRepository.findById(commentId);
            if(commentInfo.isPresent()) {
                taskCommentDTO = commentMapper.commentInfoToCommentDTO(commentInfo.get());
            }
        }
        return taskCommentDTO;
    }

    @Override
    public TaskCommentDTO saveComment(TaskCommentDTO taskCommentDTO) {
        //String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userId="1";
        TaskCommentDTO taskCommentDTOResponse=null;

        if((taskCommentDTO.getTaskId()>0) && (taskCommentDTO.getTaskComment().matches(TaskTrackerConstant.TASK_COMMENT_REGEX))){
            taskCommentDTO.setCreatedBy(userId);
            TaskComments taskComments = commentMapper.commentDTOToCommentInfo(taskCommentDTO);
            TaskComments taskCommentsResponse = commentInfoRepository.save(taskComments);
            taskCommentDTOResponse = commentMapper.commentInfoToCommentDTO(taskCommentsResponse);
        } else{
            throw new RuntimeException(TaskTrackerConstant.TASK_COMMENT_INVALID_INPUT);
        }
        log.info("taskCommentDTOResponse {}", taskCommentDTOResponse);
        return taskCommentDTOResponse;
    }

    @Override
    public List<TaskCommentDTO> getAllCommentsForTask(int taskId) {
        List<TaskCommentDTO> taskCommentDTOList = new ArrayList<TaskCommentDTO>();
        if(taskId>0){
            List<TaskComments> taskCommentsList = commentInfoRepository.findByTaskId(taskId);
            for(TaskComments taskComments : taskCommentsList){
                TaskCommentDTO output= commentMapper.commentInfoToCommentDTO(taskComments);
                taskCommentDTOList.add(output);
            }
        }
        return taskCommentDTOList;
    }
}

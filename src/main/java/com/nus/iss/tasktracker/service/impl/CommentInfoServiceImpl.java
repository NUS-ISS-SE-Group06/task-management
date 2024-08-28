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
        if(commentId>0){
            Optional<TaskComments> commentInfo = commentInfoRepository.findById(commentId);
            TaskCommentDTO taskCommentDTO = null;
            if(commentInfo.isPresent()) {
                taskCommentDTO = commentMapper.commentInfoToCommentDTO(commentInfo.get());
            } else{
                log.warn("CommentInfo Unavailable");
            }
            log.info("taskCommentDTO: {}",taskCommentDTO);
            return taskCommentDTO;
        } else{
            return null;
        }
    }

    @Override
    public TaskCommentDTO saveComment(TaskCommentDTO taskCommentDTO) {

        // FIXME - CREATED DATE NOT GETTING INSERTED: CHECK
        if((taskCommentDTO.getTaskId()>0) && (taskCommentDTO.getTaskComment().matches(TaskTrackerConstant.TASK_COMMENT_REGEX))){
            String userName = TaskTrackerInterceptor.getLoggedInUserName();
            taskCommentDTO.setCreatedBy(userName);
            log.info("commentDTO: {}", taskCommentDTO);
            TaskComments taskComments = commentMapper.commentDTOToCommentInfo(taskCommentDTO);
            log.info("commentInfo: {}", taskComments);
            TaskComments taskCommentsResponse = commentInfoRepository.save(taskComments);
            log.info("commentInfoResponse: {}", taskCommentsResponse);
            TaskCommentDTO taskCommentDTOResponse = commentMapper.commentInfoToCommentDTO(taskCommentsResponse);
            log.info("commentDTOResponse: {}", taskCommentDTOResponse);
            return taskCommentDTOResponse;
        } else{
            throw new RuntimeException(TaskTrackerConstant.TASK_COMMENT_INVALID_INPUT);
        }

    }

    @Override
    public List<TaskCommentDTO> getAllCommentsForTask(int taskId) {
        if(taskId>0){
            List<TaskComments> taskCommentsList = commentInfoRepository.findByTaskId(taskId);
            List<TaskCommentDTO> taskCommentDTOList = new ArrayList<TaskCommentDTO>();
            for(TaskComments taskComments : taskCommentsList){
                TaskCommentDTO output= commentMapper.commentInfoToCommentDTO(taskComments);
                taskCommentDTOList.add(output);
            }
            log.info("taskCommentDTOList {}", taskCommentDTOList);
            return taskCommentDTOList;
        } else {
            return null;
        }
    }
}

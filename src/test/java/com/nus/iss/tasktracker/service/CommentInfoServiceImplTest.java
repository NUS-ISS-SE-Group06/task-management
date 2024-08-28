package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.TaskCommentDTO;
import com.nus.iss.tasktracker.mapper.CommentMapper;
import com.nus.iss.tasktracker.model.TaskComments;
import com.nus.iss.tasktracker.repository.CommentInfoRepository;
import com.nus.iss.tasktracker.service.impl.CommentInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CommentInfoServiceImplTest {

    @Mock
    private CommentInfoRepository commentInfoRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentInfoServiceImpl commentInfoService;




    @Test
    public void testGetCommentById_CommentFound() {

        TaskComments comment = new TaskComments();
        comment.setTaskCommentId(1);
        comment.setTaskComment("Test Comment");

        TaskCommentDTO expectedCommentDTO = new TaskCommentDTO();
        expectedCommentDTO.setTaskCommentId(1);
        expectedCommentDTO.setTaskComment("Test Comment");

        when(commentInfoRepository.findById(1)).thenReturn(Optional.of(comment));
        when(commentMapper.commentInfoToCommentDTO(comment)).thenReturn(expectedCommentDTO);

        // Act
        TaskCommentDTO result = commentInfoService.getCommentById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCommentDTO, result);
    }


    @Test
    public void testSaveComment_ValidInput() {

        TaskCommentDTO inputCommentDTO = new TaskCommentDTO();
        inputCommentDTO.setTaskId(1);
        inputCommentDTO.setTaskComment("Valid comment");

        String userName = "testUser";
        TaskComments taskComments = new TaskComments();
        TaskCommentDTO expectedCommentDTO = new TaskCommentDTO();

        //when(TaskTrackerInterceptor.getLoggedInUserName()).thenReturn(userName);
        when(commentMapper.commentDTOToCommentInfo(inputCommentDTO)).thenReturn(taskComments);
        when(commentInfoRepository.save(taskComments)).thenReturn(taskComments);
        when(commentMapper.commentInfoToCommentDTO(taskComments)).thenReturn(expectedCommentDTO);

        // Act
        TaskCommentDTO result = commentInfoService.saveComment(inputCommentDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCommentDTO, result);
    }

}







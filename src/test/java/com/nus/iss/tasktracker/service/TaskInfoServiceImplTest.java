package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.model.KafkaTopic;
import com.nus.iss.tasktracker.model.TaskInfo;
import com.nus.iss.tasktracker.repository.TaskInfoRepository;
import com.nus.iss.tasktracker.service.impl.TaskInfoServiceImpl;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskInfoServiceImplTest {
    @Mock
    private TaskInfoRepository taskInfoRepository;

    @Mock
    private TaskInfoMapper taskInfoMapper;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private TaskInfoServiceImpl taskInfoService;


    @Test
    public void testGetAllActiveTasks_User_Success() {

        try (MockedStatic<TaskTrackerInterceptor> mocked = Mockito.mockStatic(TaskTrackerInterceptor.class)) {
            mocked.when(TaskTrackerInterceptor::getLoggedInUserName).thenReturn("admin");
            mocked.when(TaskTrackerInterceptor::getLoggedInUserRole).thenReturn(TaskTrackerConstant.REGISTRATION_ROLE_USER);


            // Mocking taskInfoRepository
            List<Object[]> taskInfoDTOList = new ArrayList<>();
            TaskInfo taskInfo=new TaskInfo();
            taskInfo.setTaskId(1);

            Object[] row =new Object[1];
            row[0]=taskInfo;

            taskInfoDTOList.add(row);

            when(taskInfoRepository.findAllByDeleteFlagAndTaskAssignee(TaskTrackerConstant.DELETE_FLAG_FALSE, 1)).thenReturn(taskInfoDTOList);

            // Testing
            List<TaskInfoDTO> result = taskInfoService.getAllActiveTasks();
            assertNotNull(result);
            assertEquals(1, result.size());


        }


    }






    @Test
    public void testGetAllActiveTasksAssignedDue_User_Success() {

        try (MockedStatic<TaskTrackerInterceptor> mocked = Mockito.mockStatic(TaskTrackerInterceptor.class)) {
            mocked.when(TaskTrackerInterceptor::getLoggedInUserName).thenReturn("admin");
            mocked.when(TaskTrackerInterceptor::getLoggedInUserRole).thenReturn(TaskTrackerConstant.REGISTRATION_ROLE_USER);


            // Mocking taskInfoRepository
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime endOfDay = currentDateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
            Timestamp currentDate = Timestamp.valueOf(endOfDay);


            List<Object[]> taskInfoDTOList = new ArrayList<>();
            TaskInfo taskInfo=new TaskInfo();
            taskInfo.setTaskId(1);
            taskInfo.setTaskName("Task1");

            Object[] row =new Object[1];
            row[0]=taskInfo;

            taskInfoDTOList.add(row);


            when(taskInfoRepository.findAllByDeleteFlagAndTaskAssigneeAndTaskDueDateLessThanEqualAndTaskStatusNot(TaskTrackerConstant.DELETE_FLAG_FALSE, 1, currentDate, TaskTrackerConstant.TASK_STATUS_COMPLETE)).thenReturn(taskInfoDTOList);

            // Testing
            List<TaskInfoDTO> result = taskInfoService.getAllActiveTasksAssignedDue();
            assertNotNull(result);
            assertEquals(1, result.size());
        }

    }



    @Test
    public void testDeleteTask_Admin_Success() {

        try (MockedStatic<TaskTrackerInterceptor> mocked = Mockito.mockStatic(TaskTrackerInterceptor.class)) {
            mocked.when(TaskTrackerInterceptor::getLoggedInUserName).thenReturn("admin");
            mocked.when(TaskTrackerInterceptor::getLoggedInUserRole).thenReturn(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN);



            // Mocking taskInfoRepository
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setTaskId(1);
            when(taskInfoRepository.findByDeleteFlagAndTaskId(eq(TaskTrackerConstant.DELETE_FLAG_FALSE), eq(1))).thenReturn(Optional.of(taskInfo));
            when(taskInfoRepository.save(any(TaskInfo.class))).thenReturn(taskInfo);

            // Mocking taskInfoMapper
            TaskInfoDTO taskInfoDTO = new TaskInfoDTO();
            when(taskInfoMapper.taskInfoToTaskinfoDTO(taskInfo)).thenReturn(taskInfoDTO);

            // Mocking KafkaProducerService
            doNothing().when(kafkaProducerService).sendMessage(any(KafkaTopic.class), anyString());

            // Testing
            TaskInfoDTO result = taskInfoService.deleteTask(1);
            assertNotNull(result);
            assertEquals(taskInfoDTO, result);
            assertEquals("TRUE",taskInfo.getDeleteFlag());
        }

    }





    @Test
    public void testCreateTask_Admin_Success() {

        try (MockedStatic<TaskTrackerInterceptor> mocked = Mockito.mockStatic(TaskTrackerInterceptor.class)) {
            mocked.when(TaskTrackerInterceptor::getLoggedInUserName).thenReturn("admin");
            mocked.when(TaskTrackerInterceptor::getLoggedInUserRole).thenReturn(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN);


            // Mocking taskInfoMapper
            TaskInfo taskInfo = new TaskInfo();
            when(taskInfoMapper.taskInfoToEntity(any(TaskInfoDTO.class))).thenReturn(taskInfo);

            // Mocking taskInfoRepository
            when(taskInfoRepository.save(any(TaskInfo.class))).thenReturn(taskInfo);

            // Mocking KafkaProducerService
            doNothing().when(kafkaProducerService).sendMessage(any(KafkaTopic.class),anyString());

            // Mocking taskInfoMapper
            TaskInfoDTO taskInfoDTO = new TaskInfoDTO();
            taskInfoDTO.setTaskName("TestName");
            taskInfoDTO.setTaskDescription("TaskDescription");
            taskInfoDTO.setTaskAssignee(1);
            //a week in the future
            taskInfoDTO.setTaskDueDate(new Timestamp(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)));

            when(taskInfoMapper.taskInfoToTaskinfoDTO(taskInfo)).thenReturn(taskInfoDTO);

            // Testing
            TaskInfoDTO result = taskInfoService.createTask(taskInfoDTO);
            assertNotNull(result);
            assertEquals(taskInfoDTO, result);
        }

    }

}




package com.nus.iss.tasktracker.service.impl;
import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.model.TaskInfo;
import com.nus.iss.tasktracker.repository.TaskInfoRepository;
import com.nus.iss.tasktracker.service.KafkaProducerService;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskInfoMapper taskInfoMapper;
    private final TaskInfoRepository taskInfoRepository;
    private final KafkaProducerService kafkaProducerService;

    public TaskInfoServiceImpl(TaskInfoMapper taskInfoMapper, TaskInfoRepository taskInfoRepository, KafkaProducerService kafkaProducerService) {
        this.taskInfoMapper = taskInfoMapper;
        this.taskInfoRepository = taskInfoRepository;
        this.kafkaProducerService=kafkaProducerService;
    }

    @Override
    @Transactional
    public TaskInfoDTO createTask(TaskInfoDTO requestDTO) {

        int userId=1;

        // Check if taskName is empty
        if (StringUtils.isEmpty(requestDTO.getTaskName())) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "Name"));
        }

        // Check if taskDescription is empty
        if (StringUtils.isEmpty(requestDTO.getTaskDescription())) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "Description"));
        }

        // Check if taskAssignee is 0
        if (requestDTO.getTaskAssignee() == 0) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "Assignee"));
        }

        // Check if taskDueDate is null
        if (requestDTO.getTaskDueDate() == null) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "DueDate"));
        }


        // Convert LocalDateTime to Timestamp

        Timestamp dueDateTimestamp = taskInfoMapper.toTimestamp(requestDTO.getTaskDueDate().toLocalDateTime());


        // Ensure the date is not in the past
        LocalDateTime now = LocalDateTime.now();
        if (requestDTO.getTaskDueDate().toLocalDateTime().isBefore(now)) {
            throw new RuntimeException("DueDate - Due date cannot be in the past!");
        }

        // Create a new TaskInfoDTO object
        TaskInfo taskInfoEntity = taskInfoMapper.taskInfoToEntity(requestDTO);

        taskInfoEntity.setTaskDueDate(dueDateTimestamp);
        // Set default value for delete flag
        taskInfoEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);

        // Get the values from the jwt token for created by/modified by
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();

        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

        if (userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            taskInfoEntity.setCreatedBy(String.valueOf(userId) );
            taskInfoEntity.setModifiedBy(String.valueOf(userId));
        }else{
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_NOT_ALLOWED);
        }

        // Set createdDate and modifiedDate
        taskInfoEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        taskInfoEntity.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        // Save and map to dto
        return taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfoEntity));
    }
public TaskInfoDTO updateTask(int taskId,TaskInfoDTO requestDTO){

        int userId=1;

        //check if taskId is valid
         if(taskId <= 0){
             throw new RuntimeException(TaskTrackerConstant.TASK_NOT_FOUND);
         }
        // Check if taskName is empty
        if (StringUtils.isEmpty(requestDTO.getTaskName())) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "Name"));
        }

        // Check if taskDescription is empty
        if (StringUtils.isEmpty(requestDTO.getTaskDescription())) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "Description"));
        }

        // Check if taskAssignee is 0
        if (requestDTO.getTaskAssignee() == 0) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "Assignee"));
        }

        // Check if taskDueDate is null
        if (requestDTO.getTaskDueDate() == null) {
            throw new RuntimeException(String.format(TaskTrackerConstant.FIELD_INVALID_INPUT, "DueDate"));
        }

        // Convert LocalDateTime to Timestamp

        Timestamp dueDateTimestamp = taskInfoMapper.toTimestamp(requestDTO.getTaskDueDate().toLocalDateTime());

        Optional<TaskInfo> optionalTaskInfo  = taskInfoRepository.findByDeleteFlagAndTaskId(TaskTrackerConstant.DELETE_FLAG_FALSE,taskId);
        TaskInfo taskInfoEntity = optionalTaskInfo.get();
        if (taskInfoEntity != null) {
            // Update taskInfoEntity with values from requestDTO
            taskInfoEntity.setTaskName(requestDTO.getTaskName());
            taskInfoEntity.setTaskDescription(requestDTO.getTaskDescription());
            taskInfoEntity.setTaskPriority(requestDTO.getTaskPriority());
            taskInfoEntity.setTaskCategoryId(requestDTO.getTaskCategoryId());
            taskInfoEntity.setTaskDueDate(dueDateTimestamp);
            taskInfoEntity.setTaskAssignee(requestDTO.getTaskAssignee());
            taskInfoEntity.setTaskRewardPoint(requestDTO.getTaskRewardPoint());
            taskInfoEntity.setTaskStatus(requestDTO.getTaskStatus());
            // Set default value for delete flag
            taskInfoEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
            // Set  modifiedDate
            taskInfoEntity.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        }else{
            throw new RuntimeException(TaskTrackerConstant.TASK_NOT_FOUND);
        }

        // Get the values from the jwt token for created by/modified by
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();

        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

        if (userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            taskInfoEntity.setCreatedBy(String.valueOf(userId));
            taskInfoEntity.setModifiedBy(String.valueOf(userId));
        }

        // Save and map to dto
        return taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfoEntity));

    }


    @Override
    @Transactional
    public TaskInfoDTO deleteTask(int id) {
        // Check for JWT and get logged-in user info
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();
        int userId=1;

        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

        // Retrieve the task info by ID
        Optional<TaskInfo> optionalTaskInfo = taskInfoRepository.findByDeleteFlagAndTaskId(TaskTrackerConstant.DELETE_FLAG_FALSE, id);
        if (optionalTaskInfo.isPresent()) {
            TaskInfo taskInfo = optionalTaskInfo.get();

            // Check if the user is admin
            if (userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                taskInfo.setCreatedBy(String.valueOf(userId));
                taskInfo.setModifiedBy(String.valueOf(userId));
            }
            else{
                throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_NOT_ALLOWED);
            }

            // Set create/modified dates
            taskInfo.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            taskInfo.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
            // Set delete flag
            taskInfo.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_TRUE);

            // Save the updated task info
            taskInfoRepository.save(taskInfo);

            // Convert TaskInfo to TaskInfoDTO and return
            return taskInfoMapper.taskInfoToTaskinfoDTO(taskInfo);
        } else {

            // If the task does not exist, throw an exception or handle the error as needed
            throw new RuntimeException(TaskTrackerConstant.TASK_NOT_FOUND);
        }
    }
    @Override
    public List<TaskInfoDTO> getAllActiveTasks(){

        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();
        int userId=1;

        if(!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)){
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

        if (userId > 0) {
            List<TaskInfoDTO> result = new ArrayList<>();

            if (userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);

                for (Object[] row: queryResult) {
                    TaskInfo taskInfo= (TaskInfo) row[0];
                    TaskInfoDTO taskInfoDTO= taskInfoMapper.taskInfoToTaskDTO(taskInfo);
                    result.add(taskInfoDTO);
                }
                //return taskInfoRepository.findAllByDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
                return result;

            } else {
                List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlagAndTaskAssignee(TaskTrackerConstant.DELETE_FLAG_FALSE, userId);

                for (Object[] row: queryResult) {
                    TaskInfo taskInfo= (TaskInfo) row[0];
                    TaskInfoDTO taskInfoDTO= taskInfoMapper.taskInfoToTaskDTO(taskInfo);
                    result.add(taskInfoDTO);
                }
                return result;
                //return taskInfoRepository.findAllByDeleteFlagAndTaskAssignee(TaskTrackerConstant.DELETE_FLAG_FALSE, currentUserInfo.getUserId());
            }
        }else{
            throw new RuntimeException(TaskTrackerConstant.USER_NOT_FOUND);
        }


    }
    @Override
    public List<TaskInfoDTO> getAllActiveTasksAssignedDue(){

        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();
        int userId =1;


        if(!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)){
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

            if (userId > 0) {
                LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time
                LocalDateTime endOfDay = currentDateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999); // Set time to end of day
                Timestamp currentDate = Timestamp.valueOf(endOfDay); // Convert to Timestamp

                List<TaskInfoDTO> result = new ArrayList<>();
                if(userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                    // Get tasks due a day before or already overdue for admin
                    List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlagAndTaskDueDateLessThanEqualAndTaskStatusNot(TaskTrackerConstant.DELETE_FLAG_FALSE, currentDate, TaskTrackerConstant.TASK_STATUS_COMPLETE);

                    for (Object[] row: queryResult) {
                        TaskInfo taskInfo= (TaskInfo) row[0];
                        TaskInfoDTO taskInfoDTO= taskInfoMapper.taskInfoToTaskDTO(taskInfo);
                        result.add(taskInfoDTO);
                    }
                    return result;

                }else{

                    // Get tasks due a day before or already overdue for non admin
                    List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlagAndTaskAssigneeAndTaskDueDateLessThanEqualAndTaskStatusNot(TaskTrackerConstant.DELETE_FLAG_FALSE, userId, currentDate,TaskTrackerConstant.TASK_STATUS_COMPLETE);

                    for (Object[] row: queryResult) {
                        TaskInfo taskInfo= (TaskInfo) row[0];
                        TaskInfoDTO taskInfoDTO= taskInfoMapper.taskInfoToTaskDTO(taskInfo);
                        result.add(taskInfoDTO);
                    }
                    return result;
                }

            }
            else{
                throw new RuntimeException(TaskTrackerConstant.USER_NOT_FOUND);
            }

        }



    @Override
    public Integer findTaskRewardPointsByGroupId(Integer userId) {
        List<Object[]> queryResult = taskInfoRepository.findTaskRewardPointsByGroupId(userId);

        if (queryResult.isEmpty()){
            return 0;
        } else {
            return  ((Number) queryResult.get(0)[1]).intValue();
        }

    }



}





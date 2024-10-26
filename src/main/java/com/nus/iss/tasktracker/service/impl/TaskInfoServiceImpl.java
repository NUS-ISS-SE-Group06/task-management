package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.model.TaskInfo;
import com.nus.iss.tasktracker.repository.TaskInfoRepository;
import com.nus.iss.tasktracker.service.KafkaProducerService;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import com.nus.iss.tasktracker.util.KafkaTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    public TaskInfoServiceImpl(TaskInfoMapper taskInfoMapper, TaskInfoRepository taskInfoRepository, KafkaProducerService kafkaProducerService) {
        this.taskInfoMapper = taskInfoMapper;
        this.taskInfoRepository = taskInfoRepository;
    }

    @Override
    @Transactional
    public TaskInfoDTO createTask(TaskInfoDTO requestDTO) {

        //Get details of the user who performed the action
        UserDTO userDTO = TaskTrackerInterceptor.getUserDetails();
        if (!StringUtils.hasText(userDTO.getName()) || !StringUtils.hasText(userDTO.getUserRole())) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
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


        // Ensure the date is not in the past
        LocalDateTime now = LocalDateTime.now();
        if (requestDTO.getTaskDueDate().toLocalDateTime().isBefore(now)) {
            throw new RuntimeException("DueDate - Due date cannot be in the past!");
        }

        // Create a new TaskInfoDTO object
        TaskInfo taskInfo = taskInfoMapper.taskInfoToEntity(requestDTO);

        taskInfo.setTaskDueDate(dueDateTimestamp);
        // Set default value for delete flag
        taskInfo.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);

        // Check if the user's role is 'ADMIN' before setting the created and modified by fields.
        // If the user is not an admin, throw an exception to deny service access
        if (userDTO.getUserRole().equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            taskInfo.setCreatedBy(userDTO.getName());
            taskInfo.setModifiedBy(userDTO.getName());
            // Set createdDate and modifiedDate
            taskInfo.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            taskInfo.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        }else{
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_NOT_ALLOWED);
        }

        // Save and map to dto
        TaskInfoDTO result = taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfo));

        return result;
    }
public TaskInfoDTO updateTask(int taskId,TaskInfoDTO requestDTO){

        //Get details of the user who performed the action
        UserDTO userDTO = TaskTrackerInterceptor.getUserDetails();
        if (!StringUtils.hasText(userDTO.getName()) || !StringUtils.hasText(userDTO.getUserRole())) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }


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
        TaskInfo taskInfo=null;
        if ( optionalTaskInfo.isPresent() ) {
            taskInfo = optionalTaskInfo.get();
        }
        if (taskInfo != null) {
            // Update taskInfo with values from requestDTO
            taskInfo.setTaskName(requestDTO.getTaskName());
            taskInfo.setTaskDescription(requestDTO.getTaskDescription());
            taskInfo.setTaskPriority(requestDTO.getTaskPriority());
            taskInfo.setTaskCategoryId(requestDTO.getTaskCategoryId());
            taskInfo.setTaskDueDate(dueDateTimestamp);
            taskInfo.setTaskAssignee(requestDTO.getTaskAssignee());
            taskInfo.setTaskRewardPoint(requestDTO.getTaskRewardPoint());
            taskInfo.setTaskStatus(requestDTO.getTaskStatus());
            // Set default value for delete flag
            taskInfo.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
            // Set  modifiedDate
            taskInfo.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        }else{
            throw new RuntimeException(TaskTrackerConstant.TASK_NOT_FOUND);
        }


        // Check if the user's role is 'ADMIN' before setting the created and modified by fields.
        // If the user is not an admin, throw an exception to deny service access
        if (userDTO.getUserRole().equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            taskInfo.setModifiedBy(userDTO.getName());
            // Set modifiedDate
            taskInfo.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        }else{
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_NOT_ALLOWED);
        }

        // Save and map to dto
        TaskInfoDTO result = taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfo));
        return result;
    }


    @Override
    @Transactional
    public TaskInfoDTO deleteTask(int id) {

        //Get details of the user who performed the action
        UserDTO userDTO = TaskTrackerInterceptor.getUserDetails();
        if (!StringUtils.hasText(userDTO.getName()) || !StringUtils.hasText(userDTO.getUserRole())) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

        // Retrieve the task info by ID
        Optional<TaskInfo> optionalTaskInfo = taskInfoRepository.findByDeleteFlagAndTaskId(TaskTrackerConstant.DELETE_FLAG_FALSE, id);
        if (optionalTaskInfo.isPresent()) {
            TaskInfo taskInfo = optionalTaskInfo.get();

            // Check if the user's role is 'ADMIN' before setting the created and modified by fields.
            // If the user is not an admin, throw an exception to deny service access
            if (userDTO.getUserRole().equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                taskInfo.setModifiedBy(userDTO.getName());
                // Set modifiedDate
                taskInfo.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
            }else{
                throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_NOT_ALLOWED);
            }

            // Set delete flag
            taskInfo.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_TRUE);

            // Save the updated task info
            taskInfoRepository.save(taskInfo);

            // Convert TaskInfo to TaskInfoDTO and return
            TaskInfoDTO result = taskInfoMapper.taskInfoToTaskinfoDTO(taskInfo);

            return  result;
        } else {

            // If the task does not exist, throw an exception or handle the error as needed
            throw new RuntimeException(TaskTrackerConstant.TASK_NOT_FOUND);
        }
    }
    @Override
    public List<TaskInfoDTO> getAllActiveTasks(){

        //Get details of the user who performed the action
        UserDTO userDTO = TaskTrackerInterceptor.getUserDetails();
        if (!StringUtils.hasText(userDTO.getName()) || !StringUtils.hasText(userDTO.getUserRole())) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }

        if (userDTO.getUserId() > 0) {
            List<TaskInfoDTO> result = new ArrayList<>();
            if (userDTO.getUserRole().equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
                for (Object[] row: queryResult) {
                    TaskInfo taskInfo= (TaskInfo) row[0];
                    TaskInfoDTO taskInfoDTO= taskInfoMapper.taskInfoToTaskDTO(taskInfo);
                    result.add(taskInfoDTO);
                }
                return result;

            } else {
                List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlagAndTaskAssignee(TaskTrackerConstant.DELETE_FLAG_FALSE, userDTO.getUserId());
                System.out.println(queryResult);
                for (Object[] row: queryResult) {
                    TaskInfo taskInfo= (TaskInfo) row[0];
                    TaskInfoDTO taskInfoDTO= taskInfoMapper.taskInfoToTaskDTO(taskInfo);
                    result.add(taskInfoDTO);
                }
                return result;
            }
        }else{
            throw new RuntimeException(TaskTrackerConstant.USER_NOT_FOUND);
        }


    }
    @Override
    public List<TaskInfoDTO> getAllActiveTasksAssignedDue(){

        //Get details of the user who performed the action
        UserDTO userDTO = TaskTrackerInterceptor.getUserDetails();
        if (!StringUtils.hasText(userDTO.getName()) || !StringUtils.hasText(userDTO.getUserRole())) {
            throw new RuntimeException(TaskTrackerConstant.SERVICE_ACCESS_WITHOUT_TOKEN);
        }


        if (userDTO.getUserId() > 0) {
            LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time
            LocalDateTime endOfDay = currentDateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999); // Set time to end of day
            Timestamp currentDate = Timestamp.valueOf(endOfDay); // Convert to Timestamp

            List<TaskInfoDTO> result = new ArrayList<>();
            if(userDTO.getUserRole().equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
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
                List<Object[]> queryResult = taskInfoRepository.findAllByDeleteFlagAndTaskAssigneeAndTaskDueDateLessThanEqualAndTaskStatusNot(TaskTrackerConstant.DELETE_FLAG_FALSE, userDTO.getUserId(), currentDate,TaskTrackerConstant.TASK_STATUS_COMPLETE);

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

    @Override
    public void releaseActiveTaskAssignedToUser(int userId){
        List<TaskInfo> tasks=taskInfoRepository.findByTaskAssigneeAndTaskStatus(userId,"Pending");

        for (TaskInfo task : tasks){
            task.setTaskAssignee(null);
            taskInfoRepository.save(task);
        }
    }

}





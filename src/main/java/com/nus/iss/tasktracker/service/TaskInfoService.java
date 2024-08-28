package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import java.util.List;

public interface TaskInfoService {

    TaskInfoDTO createTask(TaskInfoDTO requestDTO);
    TaskInfoDTO deleteTask(int id);

    List<TaskInfoDTO> getAllActiveTasks();

    TaskInfoDTO updateTask(int taskId, TaskInfoDTO requestDTO);

    List<TaskInfoDTO> getAllActiveTasksAssignedDue();

    public Integer findTaskRewardPointsByGroupId(Integer userId);
}
